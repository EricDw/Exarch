package sst.transformers

import sst.signals.ExampleActionSignal
import sst.signals.ExampleUserSignal
import TestingScope

class ExampleAbstractInterpreter
    : AbstractInterpreter<
        ExampleUserSignal,
        ExampleActionSignal>(
    coroutineScope = TestingScope,
    transform = { ExampleActionSignal }
)