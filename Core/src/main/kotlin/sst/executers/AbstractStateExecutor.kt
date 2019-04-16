package sst.executers

import kotlinx.coroutines.CoroutineScope
import signal.AbstractSignalExecutor
import sst.signals.StateSignal

abstract class AbstractStateExecutor<S : StateSignal>(
    coroutineScope: CoroutineScope,
    override val execute: (S) -> Unit
) : AbstractSignalExecutor<S>(coroutineScope)