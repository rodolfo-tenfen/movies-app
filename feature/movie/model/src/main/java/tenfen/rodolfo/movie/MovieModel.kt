package tenfen.rodolfo.movie

import android.os.Parcelable
import kotlinx.parcelize.Parcelize
import kotlinx.parcelize.RawValue
import tenfen.rodolfo.domain.movie.entity.Movie

@Parcelize
data class MovieModel(private val data: @RawValue Movie) :
    Movie by data,
    Parcelable
