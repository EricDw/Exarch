import org.spekframework.spek2.Spek
import org.spekframework.spek2.style.gherkin.Feature
import kotlin.test.assertEquals
import kotlin.test.assertFailsWith
import kotlin.test.assertTrue

internal object SpekRunsSpek : Spek({
    Feature("Using Spek to manipulate a Set<Int> as proof it runs") {
        val set by memoized { mutableSetOf<String>() }

        Scenario("adding items") {
            When("adding foo") {
                set.add("foo")
            }

            Then("it should have a size of 1") {
                assertEquals(1, set.size)
            }

            Then("it should contain foo") {
                assertTrue(set.contains("foo"))
            }
        }

        Scenario("empty") {
            Then("should have a size of 0") {
                assertEquals(0, set.size)
            }

            Then("should throw when first is invoked") {
                assertFailsWith(NoSuchElementException::class) {
                    set.first()
                }
            }
        }

        Scenario("getting the first item") {
            val item = "foo"
            Given("a non-empty set")  {
                set.add(item)
            }

            lateinit var result: String

            When("getting the first item") {
                result = set.first()
            }

            Then("it should return the first item") {
                assertEquals(item, result)
            }
        }
    }
})