package tenfen.rodolfo.moviesapp.domain

import tenfen.rodolfo.moviesapp.data.ConfigurationResponse.ImageConfigurationResponse
import tenfen.rodolfo.moviesapp.data.TmdbService

interface ImageLoadingConfigurationRepository {

    suspend fun getImageLoadingConfiguration(): ImageLoadingConfiguration
}

class CachedImageLoadingConfigurationRepository(
    private val tmdbService: TmdbService,
    private val cache: ValueCache<ImageLoadingConfiguration>,
    private val imageLoadingConfigurationFactory:
        (ImageConfigurationResponse) -> ImageLoadingConfiguration
) : ImageLoadingConfigurationRepository {

    override suspend fun getImageLoadingConfiguration() =
        cache.value
            ?: tmdbService.getConfiguration()
                .imageConfiguration
                .let(imageLoadingConfigurationFactory)
                .also { cache.value = it }
}
