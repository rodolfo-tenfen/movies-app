package tenfen.rodolfo.repository

import tenfen.rodolfo.domain.ListCache
import tenfen.rodolfo.movie.data.GenreResponse
import tenfen.rodolfo.movie.domain.Genre
import tenfen.rodolfo.movie.domain.GenreRepository
import tenfen.rodolfo.tmdb.data.TmdbService

class CachedGenreRepository(
    private val tmdbService: TmdbService,
    private val cache: ListCache<Genre>,
    private val genreFactory: (GenreResponse) -> Genre
) : GenreRepository {

    override suspend fun getGenres(): List<Genre> =
        cache.value.takeIf(List<*>::isNotEmpty)
            ?: tmdbService.getMovieGenres().genres.map(genreFactory).also(cache.value::addAll)
}
