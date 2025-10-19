package org.vocabulary.pulse.feature.add.presenter.viewmodel

import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import org.vacabulary.pulse.data.model.Word
import org.vacabulary.pulse.data.repository.WordsContentRepository
import org.vacabulary.pulse.mvi.StoreViewModel
import org.vocabulary.pulse.feature.add.presenter.model.AddWordEffect
import org.vocabulary.pulse.feature.add.presenter.model.AddWordIntent
import org.vocabulary.pulse.feature.add.presenter.model.AddWordMiddleware
import org.vocabulary.pulse.feature.add.presenter.model.AddWordState

internal class AddWordScreenViewModel(
    private val wordsContentRepository: WordsContentRepository,
    middleware: AddWordMiddleware,
): StoreViewModel<AddWordState, AddWordIntent, AddWordEffect>(
    initialUiState = AddWordState(),
    middlewares = listOf(middleware)
) {
    override fun handleIntentAndReduce(intent: AddWordIntent): Flow<Unit> =
        when (intent) {
            is AddWordIntent.WordChanged -> reduceSync { copy(word = intent.word) }
            is AddWordIntent.ExampleChanged -> reduceSync { copy( examples = intent.example) }
            is AddWordIntent.TranslationChanged -> reduceSync { copy(translation = intent.translation) }
            is AddWordIntent.Submit -> reduceAsync(
                onLoading = { currentState -> currentState.copy(isLoading = true, error = null) },
                operation = {
                    wordsContentRepository.addWord(
                        Word(
                            word = uiState.value.word,
                            translation = uiState.value.translation,
                            examples = uiState.value.examples,
                        )
                    )
                },
                onSuccess = { currentState, id ->
                    sendEffect(AddWordEffect.NavigateToHome)
                    currentState.copy(isLoading = false)
                },
                onError = { currentState, error ->
                    sendEffect(AddWordEffect.Error(error.message ?: "Unknown error"))
                    currentState.copy(isLoading = false, error = error.message)
                }
            )

            AddWordIntent.BackClicked -> flow {
                sendEffect(AddWordEffect.NavigateBack)
                emit(Unit)
            }
        }
}