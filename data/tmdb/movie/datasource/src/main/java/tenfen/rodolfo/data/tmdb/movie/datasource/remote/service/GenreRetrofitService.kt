package tenfen.rodolfo.data.tmdb.movie.datasource.remote.service

import retrofit2.http.GET
import tenfen.rodolfo.data.tmdb.movie.datasource.remote.dto.MovieGenresBody

interface GenreRetrofitService {

    @GET(value = "genre/movie/list")
    suspend fun getMovieGenres(): MovieGenresBody
}
