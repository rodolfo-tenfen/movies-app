package tenfen.rodolfo.repository.store

import tenfen.rodolfo.repository.port.ConfigurationDataSource

interface ConfigurationStore {

    suspend fun getConfiguration(): ConfigurationDataSource.ConfigurationData
}

class CacheFirstConfigurationStore(
    private val remoteDataSource: ConfigurationDataSource.Remote,
    private val memoryDataSource: ConfigurationDataSource.Memory
) : ConfigurationStore {

    override suspend fun getConfiguration(): ConfigurationDataSource.ConfigurationData =
        memoryDataSource.configuration
            ?: remoteDataSource.getConfiguration()
                .also { memoryDataSource.configuration = it }
}
