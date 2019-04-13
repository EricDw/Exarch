package sst.transformers

import signal.AbstractSignalTransformer
import sst.signals.ActionSignal
import sst.signals.UserSignal

abstract class AbstractInterpreter<A : UserSignal, B : ActionSignal>(
    override val transform: (A) -> B
) : AbstractSignalTransformer<A, B>()
