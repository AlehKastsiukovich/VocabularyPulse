package org.vocabulary.pulse.feature.add.presenter.model

import org.vacabulary.pulse.mvi.Middleware
import org.vacabulary.pulse.mvi.UiEffect
import org.vacabulary.pulse.mvi.UiIntent
import org.vacabulary.pulse.mvi.UiState

internal data class AddWordState(
    val word: String = "",
    val translation: String = "",
    val examples: List<String> = listOf(""),
    val isLoading: Boolean = false,
    val error: String? = null
) : UiState {
    val isButtonEnabled: Boolean
        get() = word.isNotBlank() && translation.isNotBlank() && !isLoading
}

internal sealed interface AddWordIntent : UiIntent {
    data class WordChanged(val word: String) : AddWordIntent
    data class TranslationChanged(val translation: String) : AddWordIntent
    data class ExampleChanged(val example: List<String>) : AddWordIntent
    data class Submit(
        val word: String,
        val translation: String,
        val examples: List<String>
    ) : AddWordIntent
    object BackClicked : AddWordIntent
}

internal sealed interface AddWordEffect : UiEffect {
    data class Error(val message: String) : AddWordEffect
    data object NavigateBack : AddWordEffect
    data object NavigateToHome : AddWordEffect
}

internal class AddWordMiddleware : Middleware<AddWordState, AddWordIntent, AddWordEffect> {
    override suspend fun processIntent(
        intent: AddWordIntent,
        stateAccessor: () -> AddWordState
    ): AddWordIntent = intent
}
