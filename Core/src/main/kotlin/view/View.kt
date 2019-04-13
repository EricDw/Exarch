package view

import command.Command
import command.CommandHandler
import kotlinx.coroutines.channels.ReceiveChannel

interface View<C: Command, S: ViewState>: ViewStateHandler<S> {
    var commandHandler: CommandHandler<C>
}