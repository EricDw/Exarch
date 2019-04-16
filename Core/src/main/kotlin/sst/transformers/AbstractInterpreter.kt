package sst.transformers

import kotlinx.coroutines.CoroutineScope
import signal.AbstractSignalTransformer
import sst.signals.ActionSignal
import sst.signals.UserSignal

abstract class AbstractInterpreter<A : UserSignal, B : ActionSignal>(
    backPressureCapacity: Int = 10,
    coroutineScope: CoroutineScope,
    override val transform: (A) -> B
) : AbstractSignalTransformer<A, B>(backPressureCapacity, coroutineScope)
