package sst.transformers

import signal.AbstractSignalTransformer
import sst.transformers.signals.ResultSignal
import sst.transformers.signals.StateSignal

abstract class AbstractReducer<A : ResultSignal, B : StateSignal>(
    transform: (A) -> B
) : AbstractSignalTransformer<A, B>(transform)

