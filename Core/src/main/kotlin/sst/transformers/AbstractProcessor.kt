package sst.transformers

import kotlinx.coroutines.CoroutineScope
import signal.AbstractSignalTransformer
import sst.signals.ActionSignal
import sst.signals.ResultSignal

abstract class AbstractProcessor<A : ActionSignal, B : ResultSignal>(
    backPressureCapacity: Int = 10,
    coroutineScope: CoroutineScope,
    override val transform: (A) -> B
) : AbstractSignalTransformer<A, B>(backPressureCapacity, coroutineScope)
