package tenfen.rodolfo.movie.data

import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class MovieGenresResponse(val genres: List<GenreResponse>)
