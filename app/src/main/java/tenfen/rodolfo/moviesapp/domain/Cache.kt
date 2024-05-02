package tenfen.rodolfo.moviesapp.domain

interface ValueCache<T> {
    var value: T?
}

interface ListCache<T> {

    val value: MutableList<T>
}

object GenreCache : ListCache<Genre> {

    override val value: MutableList<Genre> = mutableListOf()
}

object ImageLoadingConfigurationCache : ValueCache<ImageLoadingConfiguration> {

    override var value: ImageLoadingConfiguration? = null
}
