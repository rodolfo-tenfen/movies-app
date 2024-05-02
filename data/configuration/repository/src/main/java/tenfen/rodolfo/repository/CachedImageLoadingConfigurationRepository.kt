package tenfen.rodolfo.repository

import tenfen.rodolfo.configuration.data.ConfigurationResponse
import tenfen.rodolfo.configuration.domain.ImageLoadingConfiguration
import tenfen.rodolfo.configuration.domain.ImageLoadingConfigurationRepository
import tenfen.rodolfo.domain.ValueCache
import tenfen.rodolfo.tmdb.data.TmdbService

class CachedImageLoadingConfigurationRepository(
    private val tmdbService: TmdbService,
    private val cache: ValueCache<ImageLoadingConfiguration>,
    private val imageLoadingConfigurationFactory:
        (ConfigurationResponse.ImageConfigurationResponse) -> ImageLoadingConfiguration
) : ImageLoadingConfigurationRepository {

    override suspend fun getImageLoadingConfiguration() =
        cache.value
            ?: tmdbService.getConfiguration()
                .imageConfiguration
                .let(imageLoadingConfigurationFactory)
                .also { cache.value = it }
}
