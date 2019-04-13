package sst.executers

import signal.AbstractSignalExecutor
import sst.transformers.signals.StateSignal

abstract class AbstractStateExecutor<S: StateSignal>(
    execute: (S) -> Unit
) : AbstractSignalExecutor<S>(
    execute
)