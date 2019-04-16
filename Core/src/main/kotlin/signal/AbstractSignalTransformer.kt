package signal

import kotlinx.coroutines.*
import kotlinx.coroutines.channels.BroadcastChannel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.asFlow
import kotlinx.coroutines.flow.collect

abstract class AbstractSignalTransformer<A : Signal, B : Signal>(
    backPressureCapacity: Int = 10,
    private val coroutineScope: CoroutineScope
) : SignalTransformer<A, B>
{
    protected abstract val transform: (A) -> B

    @ExperimentalCoroutinesApi
    private val outChannel =
        BroadcastChannel<B>(backPressureCapacity)

    private var transformJob: Job = Job()

    @FlowPreview
    @ExperimentalCoroutinesApi
    override suspend fun transformSignals(
        inputSignals: Flow<A>
    ): Flow<B>
    {
        transformJob = coroutineScope.launch {
            inputSignals.collect {
                outChannel.send(transform(it))
            }
        }

        return outChannel.asFlow()
    }

}