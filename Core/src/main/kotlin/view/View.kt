package view

import command.Command
import kotlinx.coroutines.channels.ReceiveChannel

interface View<C: Command, S: ViewState> : ReceiveChannel<C>, ViewStateHandler<S>