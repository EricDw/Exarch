package signal

import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.FlowPreview
import kotlinx.coroutines.channels.ReceiveChannel
import kotlinx.coroutines.flow.Flow

interface SignalExecutor<A : Signal>
{
    @FlowPreview
    @ExperimentalCoroutinesApi
    suspend fun executeSignals(inputSignals: Flow<A>)
}