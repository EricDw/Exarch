package command

interface CommandHandler<C : Command>
{
    fun handleCommand(command: C)

    companion object
    {
        fun <C : Command> skeleton(handleCommandFor: (command: C) -> Unit): CommandHandler<C> =
            object : CommandHandler<C>
            {
                override fun handleCommand(command: C)
                {
                    handleCommandFor(command)
                }
            }

        fun <C : Command> emptyHandler(): CommandHandler<C> =
            object : CommandHandler<C>
            {
                override fun handleCommand(command: C)
                {
                }
            }
    }
}
