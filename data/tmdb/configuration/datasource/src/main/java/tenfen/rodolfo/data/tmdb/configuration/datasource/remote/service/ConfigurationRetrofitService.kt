package tenfen.rodolfo.data.tmdb.configuration.datasource.remote.service

import retrofit2.http.GET
import tenfen.rodolfo.data.tmdb.configuration.datasource.remote.dto.ConfigurationBody

interface ConfigurationRetrofitService {

    @GET(value = "configuration")
    suspend fun getConfiguration(): ConfigurationBody
}
