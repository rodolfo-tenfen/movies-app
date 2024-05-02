package tenfen.rodolfo.moviesapp.data

import com.squareup.moshi.Moshi
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.Request
import okhttp3.logging.HttpLoggingInterceptor
import okhttp3.logging.HttpLoggingInterceptor.Level
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import retrofit2.http.GET
import tenfen.rodolfo.moviesapp.BuildConfig

interface TmdbService {

    @GET(
        value = "discover/movie" +
            "?include_adult=false" +
            "&include_video=false" +
            "&language=en-US" +
            "&page=1" +
            "&sort_by=popularity.desc"
    )
    suspend fun getDiscoverPopularMovies(): MoviePageResponse

    @GET(value = "genre/movie/list")
    suspend fun getMovieGenres(): MovieGenresResponse

    @GET(value = "configuration")
    suspend fun getConfiguration(): ConfigurationResponse

    companion object {

        private const val BASE_URL = "https://api.themoviedb.org/3/"

        private const val ACCEPT_HEADER_NAME = "accept"
        private const val ACCEPT_HEADER_VALUE = "application/json"

        private const val AUTHORIZATION_HEADER_NAME = "Authorization"
        private const val AUTHORIZATION_HEADER_VALUE_PREFIX = "Bearer "

        fun create(): TmdbService {
            val logger = HttpLoggingInterceptor().apply { level = Level.BASIC }
            val client = OkHttpClient.Builder()
                .addInterceptor(logger)
                .addInterceptor(createRequiredHeadersInterceptor())
                .build()
            val moshi = Moshi.Builder().build()

            return Retrofit.Builder()
                .baseUrl(BASE_URL)
                .client(client)
                .addConverterFactory(MoshiConverterFactory.create(moshi))
                .build()
                .create(TmdbService::class.java)
        }

        private fun createRequiredHeadersInterceptor() =
            Interceptor { chain ->
                val original: Request = chain.request()
                val request: Request = original.newBuilder()
                    .header(ACCEPT_HEADER_NAME, ACCEPT_HEADER_VALUE)
                    .header(
                        AUTHORIZATION_HEADER_NAME,
                        "$AUTHORIZATION_HEADER_VALUE_PREFIX${BuildConfig.TMDB_API_KEY}"
                    )
                    .method(original.method, original.body)
                    .build()

                chain.proceed(request)
            }
    }
}
