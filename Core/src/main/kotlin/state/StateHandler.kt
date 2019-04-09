package state

interface StateHandler<S: State> {
    fun handleState(state: S)
}