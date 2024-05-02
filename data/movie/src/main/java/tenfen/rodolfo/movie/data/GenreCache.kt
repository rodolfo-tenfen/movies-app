package tenfen.rodolfo.movie.data

import tenfen.rodolfo.domain.ListCache
import tenfen.rodolfo.movie.domain.Genre

object GenreCache : ListCache<Genre> {

    override val value: MutableList<Genre> = mutableListOf()
}
