package user

import command.Command
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.channels.BroadcastChannel

@ExperimentalCoroutinesApi
interface CommandChannel
{
    val commands: BroadcastChannel<Command>
}