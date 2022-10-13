package dev.olshevski.navigation.reimagined

import androidx.compose.runtime.Stable

@Stable
data class NavSnapshotItem<out T, S> internal constructor(
    val hostEntry: NavHostEntry<T>,
    val scopedHostEntries: Map<S, ScopedNavHostEntry<S>>
)