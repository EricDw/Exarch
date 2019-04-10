package view

import command.Command
import state.StateHandler

abstract class AbstractViewController(
    protected val views: MutableList<View<Command, ViewState>>
) : StateHandler<ViewState>
{
    constructor(vararg views: View<Command, ViewState>) : this(views.toMutableList())
}