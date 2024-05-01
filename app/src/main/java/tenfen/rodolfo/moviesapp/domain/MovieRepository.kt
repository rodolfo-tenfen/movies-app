package tenfen.rodolfo.moviesapp.domain

import tenfen.rodolfo.moviesapp.data.MovieResponse
import tenfen.rodolfo.moviesapp.data.TmdbService

interface MovieRepository {

    suspend fun getPopularMovies(): List<Movie>
}

class RemoteMovieRepository(
    private val tmdbService: TmdbService,
    private val genreRepository: GenreRepository,
    private val imageLoadingConfigurationRepository: ImageLoadingConfigurationRepository,
    private val movieFactory: (MovieResponse, List<Genre>, ImageLoadingConfiguration) -> Movie
) : MovieRepository {

    override suspend fun getPopularMovies(): List<Movie> =
        tmdbService.getDiscoverPopularMovies()
            .results
            .map {
                movieFactory.invoke(
                    it,
                    genreRepository.getGenres(),
                    imageLoadingConfigurationRepository.getImageLoadingConfiguration()
                )
            }
}
