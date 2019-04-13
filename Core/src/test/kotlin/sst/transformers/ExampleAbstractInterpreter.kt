package sst.transformers

import sst.signals.ExampleActionSignal
import sst.signals.ExampleUserSignal

class ExampleAbstractInterpreter
    : AbstractInterpreter<
        ExampleUserSignal,
        ExampleActionSignal>(
    { ExampleActionSignal }
)