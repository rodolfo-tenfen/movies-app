package tenfen.rodolfo.data.tmdb.movie.datasource.memory

import tenfen.rodolfo.repository.port.GenreDataSource

object GenreCache : GenreDataSource.Memory {
    override var genres: List<GenreDataSource.GenreData> = emptyList()
}
