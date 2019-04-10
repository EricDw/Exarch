package view

import command.Command
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.channels.ReceiveChannel
import org.spekframework.spek2.Spek
import org.spekframework.spek2.style.gherkin.Feature
import kotlin.test.assertEquals

internal object AbstractViewControllerSpek : Spek({
    Feature("AbstractViewController") {

        Scenario("Instantiating with empty constructor") {
            var actual = 0

            val viewController: AbstractViewController by memoized {
                object : AbstractViewController()
                {
                    override fun handleState(state: ViewState)
                    {

                    }
                }
            }
            When("Calling handleState of nothing") {
                viewController.handleState(
                    object : ViewState
                    {}
                )
            }

            Then("Actual is 0") {
                assertEquals(0, actual)
            }

        }

        Scenario("Instantiating with 1 view") {
            var actual = 0

            lateinit var view1: View<Command, ViewState>

            Given("View that increases actual by 1") {
                view1 = object : View<Command, ViewState>
                {
                    override val commands: ReceiveChannel<Command> =
                        Channel()

                    override fun handleState(state: ViewState)
                    {
                        actual++
                    }
                }
            }

            val viewController: AbstractViewController by memoized {
                object : AbstractViewController(view1)
                {
                    override fun handleState(state: ViewState)
                    {
                        views.forEach { it.handleState(state) }
                    }
                }
            }

            When("Calling viewController.handleState") {
                viewController.handleState(
                    object : ViewState
                    {}
                )
            }

            Then("Actual is 1") {
                assertEquals(1, actual)
            }

        }

        Scenario("Instantiating with 2 views") {
            var actual = 0

            lateinit var view1: View<Command, ViewState>
            lateinit var view2: View<Command, ViewState>

            Given("View1 increases actual by 1") {
                view1 = object : View<Command, ViewState>
                {
                    override val commands: ReceiveChannel<Command> =
                        Channel()

                    override fun handleState(state: ViewState)
                    {
                        actual++
                    }
                }
            }

            And("View2 increases actual by 2") {
                view2 = object : View<Command, ViewState>
                {
                    override val commands: ReceiveChannel<Command> =
                        Channel()

                    override fun handleState(state: ViewState)
                    {
                        actual += 2
                    }
                }
            }

            val viewController: AbstractViewController by memoized {
                object : AbstractViewController(view1, view2)
                {
                    override fun handleState(state: ViewState)
                    {
                        views.forEach { it.handleState(state) }
                    }
                }
            }

            When("Calling viewController.handleState") {
                viewController.handleState(
                    object : ViewState
                    {}
                )
            }

            val expected = 3
            Then("Actual is $expected") {
                assertEquals(expected, actual)
            }

        }

    }
})