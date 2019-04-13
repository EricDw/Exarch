package signal

import kotlinx.coroutines.*
import kotlinx.coroutines.channels.ReceiveChannel

abstract class AbstractSignalExecutor<A : Signal>(
    protected val execute: (A) -> Unit
) : SignalExecutor<A>
{

    private var executeJob: Job = Job()

    @ExperimentalCoroutinesApi
    override suspend fun executeSignals(
        inputChannel: ReceiveChannel<A>
    )
    {
        executeJob = GlobalScope.launch(Dispatchers.Unconfined) {
            for (signal in inputChannel)
            {
                execute(signal)
            }
        }
    }

}