package tenfen.rodolfo.configuration.domain

interface ImageLoadingConfigurationRepository {

    suspend fun getImageLoadingConfiguration(): ImageLoadingConfiguration
}
