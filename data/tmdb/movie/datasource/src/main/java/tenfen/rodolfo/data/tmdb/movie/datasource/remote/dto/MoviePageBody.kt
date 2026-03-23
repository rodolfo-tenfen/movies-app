package tenfen.rodolfo.data.tmdb.movie.datasource.remote.dto

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class MoviePageBody(
    @Json(name = "page")
    val pageNumber: Int,
    val results: List<MovieBody>,
    @Json(name = "total_pages")
    val totalPages: Int
)
