package tenfen.rodolfo.data.tmdb.movie.datasource.remote.factory

import java.net.URI
import java.time.LocalDate
import java.time.format.DateTimeFormatter
import tenfen.rodolfo.data.tmdb.movie.datasource.remote.dto.MovieBody
import tenfen.rodolfo.repository.port.MovieDataSource

class MovieFactory(private val dateFormatter: DateTimeFormatter) {

    fun create(input: MovieBody): MovieDataSource.MovieData = with(input) {
        Output(
            id = id,
            title = title,
            originalTitle = originalTitle,
            overview = overview,
            backdropPath = URI(backdropPath),
            posterPath = URI(posterPath),
            releaseDate = LocalDate.parse(releaseDate, dateFormatter),
            genreIds = genreIds
        )
    }

    private data class Output(
        override val id: Int,
        override val backdropPath: URI,
        override val genreIds: List<Int>,
        override val title: String,
        override val originalTitle: String,
        override val overview: String,
        override val posterPath: URI,
        override val releaseDate: LocalDate
    ) : MovieDataSource.MovieData
}
