package org.vacabulary.pulse.mvi

/**
 * Middleware is responsible for processing intents before they reach the reducer.
 * It can be used to perform side effects, such as network requests, or to transform intents.
 *
 * @param S The type of the [UiState].
 * @param I The type of the [UiIntent].
 * @param E The type of the [UiEffect].
 */
interface Middleware<S : UiState, I : UiIntent, E : UiEffect> {
    suspend fun processIntent(intent: I, stateAccessor: () -> S): I
}