package sst.transformers

import sst.signals.ExampleActionSignal
import sst.signals.ExampleResultSignal

class ExampleAbstractProcessor : AbstractProcessor<ExampleActionSignal, ExampleResultSignal>(
    { ExampleResultSignal }
)