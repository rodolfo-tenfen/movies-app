package tenfen.rodolfo.data.tmdb.configuration.datasource.remote

import tenfen.rodolfo.data.tmdb.configuration.datasource.remote.dto.ConfigurationBody
import tenfen.rodolfo.data.tmdb.configuration.datasource.remote.service.ConfigurationRetrofitService
import tenfen.rodolfo.repository.port.ConfigurationDataSource

private typealias ConfigurationFactory =
    (ConfigurationBody.ImageConfigurationBody) -> ConfigurationDataSource.ConfigurationData

class ConfigurationRetrofitDataSource(
    private val configurationService: ConfigurationRetrofitService,
    private val configurationFactory: ConfigurationFactory
) : ConfigurationDataSource.Remote {

    override suspend fun getConfiguration(): ConfigurationDataSource.ConfigurationData =
        configurationFactory
            .invoke(configurationService.getConfiguration().imageConfiguration)
}
