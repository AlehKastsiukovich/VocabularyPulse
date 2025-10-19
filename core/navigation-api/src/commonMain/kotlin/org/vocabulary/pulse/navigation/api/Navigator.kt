package org.vocabulary.pulse.navigation.api

interface Navigator {
    fun navigate(navIntent: NavIntent)
    fun canGoBack(): Boolean
    fun back()
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

fun interface GraphContributor {
    fun contribute(into: DestinationRegistry)
}

interface DestinationRegistry {
    fun register(destination: Destination)
    val destinations: Set<Destination>
}

interface Destination {
    val route: AppRoute
}

data class NavOptions(
    val singleTop: Boolean = false,
    val restoreState: Boolean = true,
    val popUpTo: NavIntent.PopUpTo? = null
)
