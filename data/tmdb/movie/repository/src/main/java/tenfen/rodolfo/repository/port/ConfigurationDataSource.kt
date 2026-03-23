package tenfen.rodolfo.repository.port

import java.net.URI

interface ConfigurationDataSource {
    interface ConfigurationData {
        val endpoint: URI
        val posterSizeParameter: ImageSizeParameterData
        val backdropSizeParameter: ImageSizeParameterData

        interface ImageSizeParameterData {
            val value: String
        }
    }

    interface Remote : ConfigurationDataSource {

        suspend fun getConfiguration(): ConfigurationData
    }

    interface Memory : ConfigurationDataSource {
        var configuration: ConfigurationData?
    }
}
