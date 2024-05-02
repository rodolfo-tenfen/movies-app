package tenfen.rodolfo.moviesapp.data

import java.net.URI
import tenfen.rodolfo.moviesapp.data.ConfigurationResponse.ImageConfigurationResponse
import tenfen.rodolfo.moviesapp.domain.ImageLoadingConfiguration
import tenfen.rodolfo.moviesapp.domain.ImageSizeParameter

class ImageLoadingConfigurationFactory {

    fun create(input: ImageConfigurationResponse): ImageLoadingConfiguration = with(input) {
        ImageLoadingConfiguration(
            endpoint = URI.create(baseUrl).toURL(),
            backdropSizeParameter = ImageSizeParameter(backdropSizeParameters.last()),
            posterSizeParameter = ImageSizeParameter(posterSizeParameters.last())
        )
    }
}
