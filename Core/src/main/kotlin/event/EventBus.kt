package event

import bus.Bus
import kotlinx.coroutines.ExperimentalCoroutinesApi

@ExperimentalCoroutinesApi
interface EventBus: Bus<Event>