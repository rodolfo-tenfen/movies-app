package tenfen.rodolfo.data.tmdb.movie.datasource.remote

import tenfen.rodolfo.data.tmdb.movie.datasource.remote.dto.MovieBody
import tenfen.rodolfo.data.tmdb.movie.datasource.remote.service.MovieRetrofitService
import tenfen.rodolfo.repository.port.MovieDataSource

class MovieRetrofitDataSource(
    private val movieService: MovieRetrofitService,
    private val movieFactory: (MovieBody) -> MovieDataSource.MovieData
) : MovieDataSource.Remote {

    override suspend fun getPopularMovies() =
        movieService.getDiscoverPopularMovies().results.map(movieFactory)
}
