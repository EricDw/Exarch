package reducer

import javax.swing.plaf.nimbus.State


interface Reducer<I, S: state.State> {
    fun reduce(input: I, oldState: S): S
}