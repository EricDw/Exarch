import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlin.coroutines.CoroutineContext

object TestingScope : CoroutineScope
{
    override val coroutineContext: CoroutineContext =
            Dispatchers.Unconfined
}