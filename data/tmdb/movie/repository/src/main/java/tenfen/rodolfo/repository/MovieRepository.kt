package tenfen.rodolfo.repository

import kotlinx.coroutines.async
import kotlinx.coroutines.coroutineScope
import tenfen.rodolfo.domain.movie.entity.Movie
import tenfen.rodolfo.domain.movie.usecase.MoviePort
import tenfen.rodolfo.repository.port.ConfigurationDataSource
import tenfen.rodolfo.repository.port.GenreDataSource
import tenfen.rodolfo.repository.port.MovieDataSource
import tenfen.rodolfo.repository.store.ConfigurationStore
import tenfen.rodolfo.repository.store.GenreStore

class MovieRepository(
    private val configurationStore: ConfigurationStore,
    private val genreStore: GenreStore,
    private val movieDataSource: MovieDataSource.Remote,
    private val movieFactory: (
        MovieDataSource.MovieData,
        List<GenreDataSource.GenreData>,
        ConfigurationDataSource.ConfigurationData
    ) -> Movie
) : MoviePort {

    override suspend fun getPopularMovies() = coroutineScope {
        val genres = async { genreStore.getGenres() }
        val configuration = async { configurationStore.getConfiguration() }
        val movies = async { movieDataSource.getPopularMovies() }

        movies.await().map { movieFactory.invoke(it, genres.await(), configuration.await()) }
    }
}
