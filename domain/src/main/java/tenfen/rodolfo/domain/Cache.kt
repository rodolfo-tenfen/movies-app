package tenfen.rodolfo.domain

interface ValueCache<T> {
    var value: T?
}

interface ListCache<T> {

    val value: MutableList<T>
}
