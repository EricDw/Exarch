package event

interface EventHandler<E: Event> {
    fun handleEvent(event: Event)
}