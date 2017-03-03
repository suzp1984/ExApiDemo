package suzp1984.github.io.exapidemo.library


interface EventEmitter<T : Observer> {
    fun on(msg: String, listener: T)
    fun emit(msg: String, obj: Any)
}