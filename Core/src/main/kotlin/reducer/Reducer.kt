package reducer

interface Reducer<I, S: state.State> {
    fun reduce(input: I, oldState: S): S
}