package domain

import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.channels.BroadcastChannel

@ExperimentalCoroutinesApi
interface DomainModelRepresentation<T> : BroadcastChannel<T>