package tenfen.rodolfo.configuration.data

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class ConfigurationResponse(
    @Json(name = "images") val imageConfiguration: ImageConfigurationResponse
) {

    @JsonClass(generateAdapter = true)
    data class ImageConfigurationResponse(
        @Json(name = "secure_base_url") val baseUrl: String,
        @Json(name = "backdrop_sizes") val backdropSizeParameters: List<String>,
        @Json(name = "poster_sizes") val posterSizeParameters: List<String>
    )
}
