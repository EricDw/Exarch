package signal

import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.FlowPreview
import kotlinx.coroutines.channels.BroadcastChannel
import kotlinx.coroutines.channels.ReceiveChannel
import kotlinx.coroutines.flow.Flow

interface SignalTransformer<A : Signal, B : Signal>
{
    @FlowPreview
    @ExperimentalCoroutinesApi
    suspend fun transformSignals(inputSignals: Flow<A>): Flow<B>
}