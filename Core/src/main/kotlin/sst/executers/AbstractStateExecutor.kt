package sst.executers

import signal.AbstractSignalExecutor
import sst.signals.StateSignal

abstract class AbstractStateExecutor<S: StateSignal>(
    override val execute: (S) -> Unit
) : AbstractSignalExecutor<S>()