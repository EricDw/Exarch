package observable


interface Observer<T>
{
    infix fun observes(observable: Observable<T>)
}