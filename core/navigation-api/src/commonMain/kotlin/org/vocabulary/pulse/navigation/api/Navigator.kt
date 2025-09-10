package org.vocabulary.pulse.navigation.api

import androidx.compose.runtime.Composable
import androidx.compose.runtime.Immutable

interface Navigator {
    fun navigate(navIntent: NavIntent)
    fun canGoBack(): Boolean
    fun back()
}

sealed interface NavIntent {
    data class To<A : Any>(
        val route: Route<A>,
        val args: A,
        val options: NavOptions = NavOptions()
    ) : NavIntent

    object Up : NavIntent
    object Back : NavIntent
    data class Replace<A : Any>(val route: Route<A>, val args: A) : NavIntent
    data class PopUpTo(val path: String, val inclusive: Boolean = false, val saveState: Boolean = false) : NavIntent
    data class NewRoot<A : Any>(val route: Route<A>, val args: A) : NavIntent
}

interface DestinationRegistry {
    fun <A : Any> register(destination: Destination<A>)
    fun link(from: Route<*>, to: Route<*>, label: String? = null)
    val routes: Set<Route<*>>
    val destinations: Map<String, Destination<*>>
}

fun interface GraphContributor {
    fun contribute(into: DestinationRegistry)
}

interface Destination<Args : Any> {
    val route: Route<Args>
    @Composable
    fun Content(args: Args, navigator: Navigator)
}

data class UriPattern(
    val scheme: String,
    val host: String? = null,
    val path: String
)

@Immutable
data class Route<T : Any>(
    val path: String,
    val encoder: (T) -> Map<String, String> = { emptyMap() },
    val decoder: (Map<String, String>) -> T? = { null },
    val deepLinks: List<UriPattern> = emptyList(),
)

data class NavOptions(
    val singleTop: Boolean = false,
    val restoreState: Boolean = true,
    val popUpTo: NavIntent.PopUpTo? = null
)


