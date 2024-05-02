package tenfen.rodolfo.configuration.data

import tenfen.rodolfo.configuration.domain.ImageLoadingConfiguration
import tenfen.rodolfo.domain.ValueCache

object ImageLoadingConfigurationCache : ValueCache<ImageLoadingConfiguration> {

    override var value: ImageLoadingConfiguration? = null
}
