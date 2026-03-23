package tenfen.rodolfo.data.tmdb.movie.datasource.remote.service

import retrofit2.http.GET
import tenfen.rodolfo.data.tmdb.movie.datasource.remote.dto.MoviePageBody

interface MovieRetrofitService {

    @GET(
        value = "discover/movie" +
            "?include_adult=false" +
            "&include_video=false" +
            "&language=en-US" +
            "&page=1" +
            "&sort_by=popularity.desc"
    )
    suspend fun getDiscoverPopularMovies(): MoviePageBody
}
