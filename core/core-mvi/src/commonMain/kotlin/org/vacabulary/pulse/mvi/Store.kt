package org.vacabulary.pulse.mvi

import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.StateFlow

/**
 * Represents a store in the MVI (Model-View-Intent) architecture.
 *
 * A store is responsible for managing the state of a UI component and processing user intents.
 * It exposes a [StateFlow] of UI states and a [Flow] of UI effects.
 *
 * @param S The type of the UI state.
 * @param I The type of the UI intent.
 * @param E The type of the UI effect.
 */
interface Store<S : UiState, I : UiIntent, E : UiEffect> {
    val uiState: StateFlow<S>
    val uiEffect: Flow<E>
    fun dispatchIntent(intent: I): Boolean
}
