package dev.olshevski.navigation.reimagined

import androidx.annotation.VisibleForTesting
import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
import androidx.compose.runtime.key
import androidx.compose.runtime.snapshots.Snapshot

@VisibleForTesting
@Composable
internal fun <T> BaseNavHost(
    state: NavHostState<T>,
    transition: @Composable (NavSnapshot<T>) -> NavSnapshot<T>
) {
    val targetSnapshot = state.targetSnapshot

    DisposableEffect(state) {
        state.onCreate()

        onDispose {
            state.onDispose()
            state.removeOutdatedHostEntries()
        }
    }

    val currentSnapshot = key(state.id) {
        transition(targetSnapshot)

        // For NavHost: currentSnapshot is the same as targetSnapshot.
        //
        // For AnimatedNavHost: currentSnapshot is the snapshot in transition. When transition
        // finishes, currentSnapshot will become the same as targetSnapshot.
    }

    DisposableEffect(state, targetSnapshot.hostEntries.lastOrNull()) {
        onDispose {
            state.onTransitionStart()
        }
    }

    DisposableEffect(state, currentSnapshot.hostEntries.lastOrNull()) {
        if (currentSnapshot.hostEntries.lastOrNull() == targetSnapshot.hostEntries.lastOrNull()) {
            state.onTransitionFinish()
        }
        onDispose {}
    }

    DisposableEffect(state, currentSnapshot.hostEntries) {
        if (currentSnapshot.hostEntries == targetSnapshot.hostEntries) {
            state.removeOutdatedHostEntries()
        }
        onDispose {}
    }

}