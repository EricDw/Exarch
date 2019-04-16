package sst

import kotlinx.coroutines.*
import kotlinx.coroutines.channels.BroadcastChannel
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.asFlow
import org.spekframework.spek2.Spek
import org.spekframework.spek2.style.gherkin.Feature
import sst.executers.AbstractStateExecutor
import sst.executors.ExampleStateExecutor
import sst.signals.ExampleActionSignal
import sst.signals.ExampleResultSignal
import sst.signals.ExampleStateSignal
import sst.signals.ExampleUserSignal
import sst.transformers.*
import kotlin.test.assertEquals

@FlowPreview
@ExperimentalCoroutinesApi
internal object SSTExampleSpek : Spek({
    Feature("Signal State Transformation Pattern") {

        Scenario("Emitting a UserSignal after binding the components") {

            val expected = 1
            var actual = 0

            val userSignals: BroadcastChannel<ExampleUserSignal> =
                BroadcastChannel(10)

            lateinit var interpreter: AbstractInterpreter<ExampleUserSignal, ExampleActionSignal>

            Given("An ExampleInterpreter") {
                interpreter = ExampleAbstractInterpreter()
            }

            lateinit var processor: AbstractProcessor<ExampleActionSignal, ExampleResultSignal>

            And("An ExampleProcessor") {
                processor = ExampleAbstractProcessor()
            }

            lateinit var reducer: AbstractReducer<ExampleResultSignal, ExampleStateSignal>

            And("An ExampleReducer") {
                reducer = ExampleAbstractReducer()
            }

            lateinit var stateExecutor: AbstractStateExecutor<ExampleStateSignal>

            And("An ExampleStateExecutor") {
                stateExecutor = ExampleStateExecutor {
                    actual++
                }
            }

            lateinit var sstScope: SignalStateTransformationExecutor<
                    ExampleUserSignal,
                    ExampleActionSignal,
                    ExampleResultSignal,
                    ExampleStateSignal>

            And("SignalStateTransformationExecutor of components") {
                sstScope = SignalStateTransformationExecutor(
                    interpreter, processor, reducer, stateExecutor
                )
            }

            When("Binding all the given components to users signals") {
                GlobalScope.launch(Dispatchers.Unconfined) {
                    sstScope.bindTo(userSignals.asFlow())
                }
            }

            And("Sending a user signal into the system") {
                GlobalScope.launch(Dispatchers.Unconfined) {
                    userSignals.send(ExampleUserSignal)
                }
            }

            Then("$expected is output by the StateExecutor") {
                assertEquals(expected, actual)
            }

        }

        Scenario("Emitting Multiple UserSignals into the pipeline") {

            val expected = 5
            var actual = 0

            val userSignals: BroadcastChannel<ExampleUserSignal> = BroadcastChannel(10)

            val interpreter = ExampleAbstractInterpreter()
            val processor = ExampleAbstractProcessor()
            val reducer = ExampleAbstractReducer()

            val stateExecutor = ExampleStateExecutor {
                actual++
            }

            val signalStateTransformationExecutor =
                SignalStateTransformationExecutor(interpreter, processor, reducer, stateExecutor)

            Given("Pipeline is bound to the users signals") {
                GlobalScope.launch(Dispatchers.Unconfined) {
                    signalStateTransformationExecutor bindTo userSignals.asFlow()
                }
            }

            When("Sending 5 user signals into the pipeline") {
                GlobalScope.launch(Dispatchers.Unconfined) {
                    repeat(5) {
                        userSignals.send(ExampleUserSignal)
                    }
                }
            }

            Then("$expected signals are output by the StateExecutor") {
                assertEquals(expected, actual)
            }

        }
    }
})