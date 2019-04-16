package sst.transformers

import kotlinx.coroutines.CoroutineScope
import signal.AbstractSignalTransformer
import sst.signals.ResultSignal
import sst.signals.StateSignal

abstract class AbstractReducer<A : ResultSignal, B : StateSignal>(
    backPressureCapacity: Int = 10,
    coroutineScope: CoroutineScope,
    initialStateSignal: B,
    reduce: (oldStateSignal: B, resultSignal: A) -> B
) : AbstractSignalTransformer<A, B>(backPressureCapacity, coroutineScope)
{

    private var oldState = initialStateSignal

    override val transform: (resultSignal: A) -> B = transform@{ resultSignal: A ->
        oldState = reduce(oldState, resultSignal)
        return@transform oldState
    }
}

