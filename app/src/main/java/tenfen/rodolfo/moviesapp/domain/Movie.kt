package tenfen.rodolfo.moviesapp.domain

import java.net.URL
import java.time.LocalDate

data class Movie(
    val title: String,
    val originalTitle: String,
    val overview: String,
    val backdropUrl: URL,
    val posterUrl: URL,
    val releaseDate: LocalDate,
    val genres: List<Genre>
)
