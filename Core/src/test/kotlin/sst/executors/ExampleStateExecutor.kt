package sst.executors

import sst.executers.AbstractStateExecutor
import sst.signals.ExampleStateSignal
import TestingScope

class ExampleStateExecutor(
    execute: (ExampleStateSignal) -> Unit
) : AbstractStateExecutor<ExampleStateSignal>(TestingScope, execute)