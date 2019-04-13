package domain

import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.channels.BroadcastChannel
import result.Result

@ExperimentalCoroutinesApi
interface DomainModel {
    val results: BroadcastChannel<Result>
}