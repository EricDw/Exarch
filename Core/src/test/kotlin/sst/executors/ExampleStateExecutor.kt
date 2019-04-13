package sst.executors

import sst.signals.ExampleStateSignal
import sst.executers.AbstractStateExecutor

class ExampleStateExecutor(
    execute: (ExampleStateSignal) -> Unit
) : AbstractStateExecutor<ExampleStateSignal>(execute)