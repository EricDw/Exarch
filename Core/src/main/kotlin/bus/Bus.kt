package bus

import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.channels.BroadcastChannel

@ExperimentalCoroutinesApi
interface Bus<T>: BroadcastChannel<T>