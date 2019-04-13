import command.Command
import command.CommandBus
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.channels.BroadcastChannel

@ExperimentalCoroutinesApi
abstract class AbstractCommandBus(
    val scope: CoroutineScope
): CommandBus, BroadcastChannel<Command> by BroadcastChannel(99)