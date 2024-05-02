package tenfen.rodolfo.repository

import tenfen.rodolfo.configuration.domain.ImageLoadingConfiguration
import tenfen.rodolfo.configuration.domain.ImageLoadingConfigurationRepository
import tenfen.rodolfo.movie.data.MovieResponse
import tenfen.rodolfo.movie.domain.Genre
import tenfen.rodolfo.movie.domain.GenreRepository
import tenfen.rodolfo.movie.domain.Movie
import tenfen.rodolfo.movie.domain.MovieRepository
import tenfen.rodolfo.tmdb.data.TmdbService

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
