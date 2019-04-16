package sst.transformers

import sst.signals.ExampleActionSignal
import sst.signals.ExampleResultSignal
import TestingScope

class ExampleAbstractProcessor
    : AbstractProcessor<ExampleActionSignal, ExampleResultSignal>(
    coroutineScope = TestingScope,
    transform = { ExampleResultSignal }
)