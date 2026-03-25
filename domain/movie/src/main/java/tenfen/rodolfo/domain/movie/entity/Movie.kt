package tenfen.rodolfo.domain.movie.entity

import java.net.URI
import java.time.LocalDate

interface Movie {
    val title: String
    val originalTitle: String
    val overview: String
    val backdropUrl: URI?
    val posterUrl: URI
    val releaseDate: LocalDate
    val genres: List<Genre>
}
