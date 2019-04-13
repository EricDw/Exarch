import command.Command
import command.CommandBus
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.launch
import org.spekframework.spek2.Spek
import org.spekframework.spek2.style.gherkin.Feature
import kotlin.coroutines.CoroutineContext
import kotlin.test.assertEquals

@ExperimentalCoroutinesApi
object CommandBusSpek : Spek({
    Feature("AbstractCommandBus") {
        lateinit var scope: CoroutineScope
        lateinit var commandBus: CommandBus

        Scenario("Sending Commands") {
            val expected = 1
            var actual = 0
            Given("CoroutineScope of Dispatchers.Unconfined") {
                scope = object : CoroutineScope
                {
                    override val coroutineContext: CoroutineContext =
                        Dispatchers.Unconfined
                }
            }

            And("CommandBus instantiated with scope") {
                commandBus = object : AbstractCommandBus(scope)
                {}
            }

            When("Sending Command") {
                scope.launch {
                    commandBus.send(object: Command {})
                }
            }

            And("Opening a subscription") {
                scope.launch {
                    for (command in commandBus.openSubscription()) {
                        actual++
                    }
                }
            }

            Then("Command is received from subscription") {
                assertEquals(expected, actual)
            }
        }
    }
})