package tenfen.rodolfo.movie

import android.os.Parcelable
import kotlinx.parcelize.Parcelize
import tenfen.rodolfo.domain.movie.entity.Genre

@JvmInline
@Parcelize
value class GenreModel(override val name: String) :
    Genre,
    Parcelable {

    constructor(genre: Genre) : this(name = genre.name)
}
