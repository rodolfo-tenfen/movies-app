package tenfen.rodolfo.configuration.data

import java.net.URL
import tenfen.rodolfo.configuration.data.ConfigurationResponse.ImageConfigurationResponse
import tenfen.rodolfo.configuration.domain.ImageLoadingConfiguration
import tenfen.rodolfo.configuration.domain.ImageSizeParameter

class ImageLoadingConfigurationFactory {

    fun create(input: ImageConfigurationResponse): ImageLoadingConfiguration = with(input) {
        ImageLoadingConfiguration(
            endpoint = URL(baseUrl),
            backdropSizeParameter = ImageSizeParameter(backdropSizeParameters.last()),
            posterSizeParameter = ImageSizeParameter(posterSizeParameters.last())
        )
    }
}
