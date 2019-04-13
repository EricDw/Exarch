package state

import command.Command

interface State {
    fun observe(command: Command)
}