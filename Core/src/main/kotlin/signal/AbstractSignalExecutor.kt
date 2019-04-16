package signal

import kotlinx.coroutines.*
import kotlinx.coroutines.channels.ReceiveChannel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.collect

abstract class AbstractSignalExecutor<A : Signal>(
    private val coroutineScope: CoroutineScope
) : SignalExecutor<A>
{
    protected abstract val execute: (A) -> Unit

    private var executeJob: Job = Job()

    @FlowPreview
    @ExperimentalCoroutinesApi
    override suspend fun executeSignals(
        inputSignals: Flow<A>
    )
    {
        executeJob = coroutineScope.launch {
            inputSignals.collect {
                execute(it)
            }
        }
    }

}