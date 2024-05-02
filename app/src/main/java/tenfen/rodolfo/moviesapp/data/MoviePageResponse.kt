package tenfen.rodolfo.moviesapp.data

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class MoviePageResponse(
    @Json(name = "page") val pageNumber: Int,
    val results: List<MovieResponse>,
    @Json(name = "total_pages") val totalPages: Int
)
