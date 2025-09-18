package org.vocabulary.pulse

import androidx.compose.ui.window.ComposeUIViewController
import org.vocabulary.pulse.di.KoinApp

fun MainViewController() = ComposeUIViewController { KoinApp() }