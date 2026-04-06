package tenfen.rodolfo.movie

import android.os.Parcelable
import java.net.URI
import java.time.LocalDate
import kotlinx.parcelize.Parcelize
import tenfen.rodolfo.domain.movie.entity.Movie

@Parcelize
data class MovieModel(
    override val title: String,
    override val originalTitle: String,
    override val overview: String,
    override val backdropUrl: URI?,
    override val posterUrl: URI,
    override val releaseDate: LocalDate,
    override val genres: List<GenreModel>
) : Movie,
    Parcelable {

    constructor(movie: Movie) : this(
        movie.title,
        movie.originalTitle,
        movie.overview,
        movie.backdropUrl,
        movie.posterUrl,
        movie.releaseDate,
        movie.genres.map(::GenreModel)
    )
}
