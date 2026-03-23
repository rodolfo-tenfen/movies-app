package tenfen.rodolfo.domain.movie.usecase

import tenfen.rodolfo.domain.movie.entity.Movie

interface MoviePort {

    suspend fun getPopularMovies(): List<Movie>
}
