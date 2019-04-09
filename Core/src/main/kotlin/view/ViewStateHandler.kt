package view

import state.StateHandler

interface ViewStateHandler<S: ViewState> : StateHandler<S>