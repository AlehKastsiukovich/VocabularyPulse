package org.vocabulary.pulse.navigation.api

interface Navigator {
    fun navigate(navIntent: NavIntent)
    fun canGoBack(): Boolean
    fun back()
}

class NavigatorHolder : Navigator {
    private var delegate: Navigator? = null
    private val pendingCommands = mutableListOf<PendingCommand>()

    fun attach(navigator: Navigator) {
        delegate = navigator
        pendingCommands.forEach { command ->
            when (command) {
                is PendingCommand.Navigate -> navigator.navigate(command.navIntent)
                PendingCommand.Back -> navigator.back()
            }
        }
        pendingCommands.clear()
    }

    fun detach(navigator: Navigator) {
        if (delegate === navigator) {
            delegate = null
        }
    }

    override fun navigate(navIntent: NavIntent) {
        val current = delegate
        if (current == null) {
            pendingCommands += PendingCommand.Navigate(navIntent)
            return
        }
        current.navigate(navIntent)
    }

    override fun canGoBack(): Boolean = delegate?.canGoBack() ?: false

    override fun back() {
        val current = delegate
        if (current == null) {
            pendingCommands += PendingCommand.Back
            return
        }
        current.back()
    }

    private sealed interface PendingCommand {
        data class Navigate(val navIntent: NavIntent) : PendingCommand
        object Back : PendingCommand
    }
}

sealed interface NavIntent {
    data class To(
        val route: AppRoute,
        val options: NavOptions = NavOptions()
    ) : NavIntent

    object Up : NavIntent
    object Back : NavIntent

    data class Replace(val route: AppRoute) : NavIntent

    data class PopUpTo(
        val route: AppRoute,
        val inclusive: Boolean = false,
        val saveState: Boolean = false
    ) : NavIntent

    data class NewRoot(val route: AppRoute) : NavIntent
}

data class NavOptions(
    val singleTop: Boolean = false,
    val restoreState: Boolean = true,
    val popUpTo: NavIntent.PopUpTo? = null
)
