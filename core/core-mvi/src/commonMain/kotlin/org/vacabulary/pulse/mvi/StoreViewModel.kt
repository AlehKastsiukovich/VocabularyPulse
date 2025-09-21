package org.vacabulary.pulse.mvi

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.SupervisorJob
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.flatMapMerge
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

abstract class StoreViewModel<S : UiState, I : UiIntent, E : UiEffect>(
    initialUiState: S,
    private val middlewares: List<Middleware<S, I, E>> = emptyList()
) : ViewModel(), Store<S, I, E> {

    protected val storeScope: CoroutineScope =
        CoroutineScope(viewModelScope.coroutineContext + SupervisorJob() + Dispatchers.Main.immediate)

    private val _uiState: MutableStateFlow<S> = MutableStateFlow(initialUiState)
    override val uiState: StateFlow<S> = _uiState.asStateFlow()

    private val _uiEffect: Channel<E> = Channel(Channel.BUFFERED)
    override val uiEffect: Flow<E> = _uiEffect.receiveAsFlow()

    private val intentsToProcessFlow: MutableSharedFlow<I> =
        MutableSharedFlow(extraBufferCapacity = 64)

    init {
        storeScope.launch {
            intentsToProcessFlow
                .onEach { intent ->
                    var currentIntent: I = intent
                    for (middleware in middlewares) {
                        currentIntent = middleware.processIntent(currentIntent) { _uiState.value }
                    }
                }
                .flatMapMerge(concurrency = Int.MAX_VALUE) { intent ->
                    handleIntentAndReduce(intent)
                }
                .collect()
        }
    }

    override fun dispatchIntent(intent: I): Boolean = intentsToProcessFlow.tryEmit(intent)

    protected abstract fun handleIntentAndReduce(intent: I): Flow<Unit>

    protected fun reduceState(reducer: (currentState: S) -> S) {
        val oldState = _uiState.value
        _uiState.update(reducer)
        val newState = _uiState.value
    }

    protected suspend fun sendEffect(effect: E) {
        _uiEffect.send(effect)
    }

    protected fun reduceSync(
        processor: S.() -> S
    ): Flow<Unit> = flow {
        reduceState(processor)
        emit(Unit)
    }

    protected fun <R> reduceAsync(
        onLoading: ((currentState: S) -> S)? = null,
        operation: suspend () -> Result<R>,
        onSuccess: (currentState: S, result: R) -> S,
        onError: ((currentState: S, error: Throwable) -> S)? = null
    ): Flow<Unit> = flow {
        onLoading?.let { reduceState(it) }
        operation()
            .onSuccess { result ->
                reduceState { currentState -> onSuccess(currentState, result) }
            }
            .onFailure { error ->
                onError?.let { reduceState { currentState -> it(currentState, error) } }
                    ?: reduceState { currentState ->
                        currentState
                    }
            }
        emit(Unit)
    }
}
