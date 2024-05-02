package tenfen.rodolfo.moviesapp.data

import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class GenreResponse(val id: Int, val name: String)
