package tenfen.rodolfo.data.tmdb.configuration.datasource.remote.factory

import java.net.URI
import tenfen.rodolfo.data.tmdb.configuration.datasource.remote.dto.ConfigurationBody.ImageConfigurationBody
import tenfen.rodolfo.repository.port.ConfigurationDataSource.ConfigurationData

class ConfigurationFactory {

    fun create(input: ImageConfigurationBody): ConfigurationData = with(input) {
        Output(
            endpoint = URI(baseUrl),
            backdropSizeParameter = Output.ImageSizeParameter(backdropSizeParameters.last()),
            posterSizeParameter = Output.ImageSizeParameter(posterSizeParameters.last())
        )
    }

    private data class Output(
        override val endpoint: URI,
        override val posterSizeParameter: ConfigurationData.ImageSizeParameterData,
        override val backdropSizeParameter: ConfigurationData.ImageSizeParameterData
    ) : ConfigurationData {

        @JvmInline
        value class ImageSizeParameter(override val value: String) :
            ConfigurationData.ImageSizeParameterData
    }
}
