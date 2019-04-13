package sst.transformers

import signal.AbstractSignalTransformer
import sst.signals.ActionSignal
import sst.signals.ResultSignal

abstract class AbstractProcessor<A : ActionSignal, B : ResultSignal>(
    override val transform: (A) -> B
) : AbstractSignalTransformer<A, B>()
