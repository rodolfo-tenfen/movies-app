package tenfen.rodolfo.data.tmdb.movie.datasource.remote.dto

import com.squareup.moshi.JsonClass
import tenfen.rodolfo.repository.port.GenreDataSource

@JsonClass(generateAdapter = true)
data class GenreBody(override val id: Int, override val name: String) :
    GenreDataSource.GenreData
