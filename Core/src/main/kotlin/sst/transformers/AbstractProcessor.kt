package sst.transformers

import signal.AbstractSignalTransformer
import sst.transformers.signals.ActionSignal
import sst.transformers.signals.ResultSignal

abstract class AbstractProcessor<A : ActionSignal, B : ResultSignal>(
    transform: (A) -> B
) : AbstractSignalTransformer<A, B>(transform)
