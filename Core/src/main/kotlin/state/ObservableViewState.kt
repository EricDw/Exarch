package state

import view.ViewState
import kotlin.properties.ObservableProperty

interface ObservableViewState<S: ViewState>
{
    var state: ObservableProperty<S>
}