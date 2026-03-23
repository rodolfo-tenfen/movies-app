package tenfen.rodolfo.data.tmdb.configuration.datasource.memory

import tenfen.rodolfo.repository.port.ConfigurationDataSource

object ConfigurationCache : ConfigurationDataSource.Memory {

    override var configuration: ConfigurationDataSource.ConfigurationData? = null
}
