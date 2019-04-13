package signal

import kotlinx.coroutines.*
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.channels.ReceiveChannel

abstract class AbstractSignalTransformer<A : Signal, B : Signal>(
    protected val transform: (A) -> B
) : SignalTransformer<A, B>
{

    private val outChannel = Channel<B>(Channel.UNLIMITED)

    private var transformJob: Job = Job()

    @ExperimentalCoroutinesApi
    override suspend fun transformSignals(
        inputChannel: ReceiveChannel<A>
    ): ReceiveChannel<B>
    {
        transformJob = GlobalScope.launch(Dispatchers.Unconfined) {
            for (signal in inputChannel)
            {
                outChannel.send(transform(signal))
            }
        }

        return outChannel
    }

}