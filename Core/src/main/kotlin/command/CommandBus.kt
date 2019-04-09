package command

import bus.Bus
import kotlinx.coroutines.ExperimentalCoroutinesApi

@ExperimentalCoroutinesApi
interface CommandBus : Bus<Command>