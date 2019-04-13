package signal

import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.channels.ReceiveChannel

interface SignalExecutor<A : Signal>
{
    @ExperimentalCoroutinesApi
    suspend fun executeSignals(inputChannel: ReceiveChannel<A>)
}