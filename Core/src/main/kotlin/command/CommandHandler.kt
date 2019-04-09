package command

interface CommandHandler<C: Command> {
    fun handleCommand(command: Command)
}