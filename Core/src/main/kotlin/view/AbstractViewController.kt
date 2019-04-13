package view

import command.Command
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.channels.ReceiveChannel
import state.StateHandler

abstract class AbstractViewController(
    protected val backingChannel: Channel<Command> = Channel(99),
    protected val views: MutableList<View<Command, ViewState>> = mutableListOf()
) : StateHandler<ViewState>, ReceiveChannel<Command> by backingChannel
{
    constructor(
        vararg views: View<Command, ViewState> = emptyArray()
    ) : this(views = views.toMutableList())
}