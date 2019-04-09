package state

interface StateHolder<S : State> {
    val state: S
}