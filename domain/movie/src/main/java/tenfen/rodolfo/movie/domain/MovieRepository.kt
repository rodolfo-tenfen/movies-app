package tenfen.rodolfo.movie.domain

interface MovieRepository {

    suspend fun getPopularMovies(): List<Movie>
}
