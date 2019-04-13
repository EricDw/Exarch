package sst.transformers

import signal.AbstractSignalTransformer
import sst.transformers.signals.ActionSignal
import sst.transformers.signals.UserSignal

abstract class AbstractInterpreter<A : UserSignal, B : ActionSignal>(
    transform: (A) -> B
) : AbstractSignalTransformer<A, B>(transform)
