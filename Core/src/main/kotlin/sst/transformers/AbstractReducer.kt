package sst.transformers

import signal.AbstractSignalTransformer
import sst.signals.ResultSignal
import sst.signals.StateSignal
import state.State

abstract class AbstractReducer<A : ResultSignal, B : StateSignal>(
    initialStateSignal: B,
    reduce: (oldStateSignal: B, resultSignal: A) -> B
) : AbstractSignalTransformer<A, B>()
{

    private var oldState = initialStateSignal

    override val transform: (resultSignal: A) -> B = transform@{ resultSignal: A ->
        oldState = reduce(oldState, resultSignal)
        return@transform oldState
    }
}

