package view

abstract class AbstractPresenter<S: ViewState>(
    protected val viewController: ViewController<S>,
    protected val viewStateHolder: ViewStateHolder<S>
)