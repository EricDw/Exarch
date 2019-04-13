package sst

import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.channels.ReceiveChannel
import kotlinx.coroutines.channels.broadcast
import signal.SignalExecutor
import signal.SignalTransformer
import sst.transformers.signals.ActionSignal
import sst.transformers.signals.ResultSignal
import sst.transformers.signals.StateSignal
import sst.transformers.signals.UserSignal

class SignalStateTransformationExecutor<
        US : UserSignal,
        AS : ActionSignal,
        RS : ResultSignal,
        SS : StateSignal>(
    val interpreter: SignalTransformer<US, AS>,
    val processor: SignalTransformer<AS, RS>,
    val reducer: SignalTransformer<RS, SS>,
    val stateExecutor: SignalExecutor<SS>
)

@ExperimentalCoroutinesApi
suspend infix fun <SS : StateSignal,
        US : UserSignal,
        AS : ActionSignal,
        RS : ResultSignal>
        SignalStateTransformationExecutor<US, AS, RS, SS>.bindTo(
    userSignals: ReceiveChannel<US>
): Unit =
    stateExecutor.executeSignals(
        reducer.transformSignals(
            processor.transformSignals(
                interpreter.transformSignals(userSignals).broadcastSubscription()
            ).broadcastSubscription()
        ).broadcastSubscription()
    )

@ExperimentalCoroutinesApi
private fun <A> ReceiveChannel<A>.broadcastSubscription(
    capacity: Int = 10
): ReceiveChannel<A> = broadcast(capacity).openSubscription()
