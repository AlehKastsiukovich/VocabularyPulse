package org.vocabulary.pulse.navigation.compose

import org.vocabulary.pulse.navigation.api.Destination
import org.vocabulary.pulse.navigation.api.DestinationRegistry

class ComposeDestinationRegistry : DestinationRegistry {
    private val _destinations = linkedSetOf<Destination>()
    override fun register(destination: Destination) { _destinations.add(destination) }
    override val destinations: Set<Destination> get() = _destinations
}
