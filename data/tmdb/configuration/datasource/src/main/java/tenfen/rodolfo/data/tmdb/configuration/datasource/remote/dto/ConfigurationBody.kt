package tenfen.rodolfo.data.tmdb.configuration.datasource.remote.dto

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class ConfigurationBody(
    @Json(name = "images") val imageConfiguration: ImageConfigurationBody
) {

    @JsonClass(generateAdapter = true)
    data class ImageConfigurationBody(
        @Json(name = "secure_base_url") val baseUrl: String,
        @Json(name = "backdrop_sizes") val backdropSizeParameters: List<String>,
        @Json(name = "poster_sizes") val posterSizeParameters: List<String>
    )
}
