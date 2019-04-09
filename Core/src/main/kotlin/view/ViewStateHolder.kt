package view

import state.StateHolder

interface ViewStateHolder<S: ViewState> : StateHolder<S>