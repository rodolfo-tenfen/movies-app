package tenfen.rodolfo.movie.domain

interface GenreRepository {

    suspend fun getGenres(): List<Genre>
}
