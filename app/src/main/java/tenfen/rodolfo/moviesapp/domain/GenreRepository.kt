package tenfen.rodolfo.moviesapp.domain

import tenfen.rodolfo.moviesapp.data.GenreResponse
import tenfen.rodolfo.moviesapp.data.TmdbService

interface GenreRepository {

    suspend fun getGenres(): List<Genre>
}

class CachedGenreRepository(
    private val tmdbService: TmdbService,
    private val cache: ListCache<Genre>,
    private val genreFactory: (GenreResponse) -> Genre
) : GenreRepository {

    override suspend fun getGenres(): List<Genre> =
        cache.value.takeIf(List<*>::isNotEmpty)
            ?: tmdbService.getMovieGenres().genres.map(genreFactory).also(cache.value::addAll)
}
