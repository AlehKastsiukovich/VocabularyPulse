package org.vocabulary.pulse

import androidx.compose.ui.window.Window
import androidx.compose.ui.window.application
import org.vocabulary.pulse.di.KoinApp

fun main() = application {
    Window(
        onCloseRequest = ::exitApplication,
        title = "VocaPulse",
    ) {
        KoinApp()
    }
}