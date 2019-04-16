package sst

import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.FlowPreview
import kotlinx.coroutines.flow.Flow
import signal.SignalExecutor
import signal.SignalTransformer
import sst.signals.ActionSignal
import sst.signals.ResultSignal
import sst.signals.StateSignal
import sst.signals.UserSignal

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

@FlowPreview
@ExperimentalCoroutinesApi
suspend infix fun <SS : StateSignal,
        US : UserSignal,
        AS : ActionSignal,
        RS : ResultSignal>
        SignalStateTransformationExecutor<US, AS, RS, SS>.bindTo(
    userSignals: Flow<US>
): Unit =
    stateExecutor.executeSignals(
        reducer.transformSignals(
            processor.transformSignals(
                interpreter.transformSignals(userSignals)
            )
        )
    )