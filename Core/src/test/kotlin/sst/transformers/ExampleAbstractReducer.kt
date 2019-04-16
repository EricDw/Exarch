package sst.transformers

import sst.signals.ExampleResultSignal
import sst.signals.ExampleStateSignal
import TestingScope

class ExampleAbstractReducer
    : AbstractReducer<
        ExampleResultSignal,
        ExampleStateSignal>(
    coroutineScope = TestingScope,
    initialStateSignal = ExampleStateSignal,
    reduce = { _, _ -> ExampleStateSignal }
)