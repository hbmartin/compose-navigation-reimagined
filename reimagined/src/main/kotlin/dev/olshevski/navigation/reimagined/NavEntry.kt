package dev.olshevski.navigation.reimagined

import androidx.compose.runtime.Stable
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.ViewModelStore
import androidx.savedstate.SavedStateRegistry

/**
 * A single item of a backstack with its own identity. The backstack may contain several equal
 * destinations, and this wrapper class helps differentiate them.
 *
 * The identity is preserved by [id] property. Every entry with a unique `id` in the NavHost will
 * be served its own set of components such as a [Lifecycle], a [SavedStateRegistry] and
 * a [ViewModelStore]. Additionally, it will be able to remember all saveables within
 * composition (`rememberSaveable`).
 */
@Stable
class NavEntry<out T> internal constructor(

    /**
     * Autogenerated unique ID.
     */
    val id: NavId = NavId(),

    /**
     * A destination you passed into [navigate], [replaceLast] or other extension method.
     * If you used [NavController.setNewBackstack] directly this is the destination
     * you passed into [navEntry] method.
     */
    val destination: T
) {

    override fun toString() = "NavEntry(id=$id, destination=$destination)"

    operator fun component1() = id

    operator fun component2() = destination

}

/**
 * Creates a new [NavEntry] with a new unique id and the specified [destination].
 */
fun <T> navEntry(destination: T) = NavEntry(destination = destination)