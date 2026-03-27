package tenfen.rodolfo.repository.port

import java.net.URI
import java.time.LocalDate

interface MovieDataSource {

    interface MovieData {
        val id: Int
        val backdropPath: URI?
        val genreIds: List<Int>
        val title: String
        val originalTitle: String
        val overview: String
        val posterPath: URI
        val releaseDate: LocalDate
    }

    interface Remote {

        suspend fun getPopularMovies(): List<MovieData>
    }
}
