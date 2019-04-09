package query

import bus.Bus
import kotlinx.coroutines.ExperimentalCoroutinesApi

@ExperimentalCoroutinesApi
interface QueryBus : Bus<Query>