package signal

import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.channels.BroadcastChannel
import kotlinx.coroutines.channels.ReceiveChannel

interface SignalTransformer<A : Signal, B : Signal>
{
    @ExperimentalCoroutinesApi
    suspend fun transformSignals(inputChannel: ReceiveChannel<A>): ReceiveChannel<B>
}