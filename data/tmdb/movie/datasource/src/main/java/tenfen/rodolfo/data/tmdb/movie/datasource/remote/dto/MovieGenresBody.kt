package tenfen.rodolfo.data.tmdb.movie.datasource.remote.dto

import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class MovieGenresBody(val genres: List<GenreBody>)
