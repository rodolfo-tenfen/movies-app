package tenfen.rodolfo.data.tmdb.movie.datasource.remote

import tenfen.rodolfo.data.tmdb.movie.datasource.remote.service.GenreRetrofitService
import tenfen.rodolfo.repository.port.GenreDataSource

class GenreRetrofitDataSource(private val genreService: GenreRetrofitService) :
    GenreDataSource.Remote {

    override suspend fun getGenres(): List<GenreDataSource.GenreData> =
        genreService.getMovieGenres().genres
}
