package org.vacabulary.pulse.mvi

/**
 * Represents the state of the UI at any given time.
 * This interface should be implemented by classes that hold the data
 * to be displayed on the screen.
 */
interface UiState

/**
 * Represents an intent from the UI.
 *
 * This interface is a marker interface, meaning it doesn't declare any methods or properties.
 * It's used to group different UI intents under a common type.
 *
 * For example, in a login screen, you might have `LoginClickedIntent` or `ForgotPasswordClickedIntent`
 * which would both implement this `UiIntent` interface.
 */
interface UiIntent

/**
 * Represents a side effect that can be triggered from a ViewModel and observed by the UI.
 * UiEffects are typically used for actions that should happen once and not be tied to the state,
 * such as showing a toast, navigating to another screen, or displaying a dialog.
 */
interface UiEffect