package sst.transformers

import sst.signals.ExampleResultSignal
import sst.signals.ExampleStateSignal

class ExampleAbstractReducer
    : AbstractReducer<
        ExampleResultSignal,
        ExampleStateSignal>(
    ExampleStateSignal,
    { _, _ -> ExampleStateSignal }
)