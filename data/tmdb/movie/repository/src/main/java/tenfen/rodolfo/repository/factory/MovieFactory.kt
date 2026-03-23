package tenfen.rodolfo.repository.factory

import java.net.URI
import java.time.LocalDate
import tenfen.rodolfo.domain.movie.entity.Genre
import tenfen.rodolfo.domain.movie.entity.Movie as MovieEntity
import tenfen.rodolfo.repository.port.ConfigurationDataSource
import tenfen.rodolfo.repository.port.GenreDataSource
import tenfen.rodolfo.repository.port.MovieDataSource

class MovieFactory {

    fun create(
        input: MovieDataSource.MovieData,
        genres: List<GenreDataSource.GenreData>,
        imageLoadingConfiguration: ConfigurationDataSource.ConfigurationData
    ): MovieEntity = with(input) {
        Output(
            title = title,
            originalTitle = originalTitle,
            overview = overview,
            backdropUrl = with(imageLoadingConfiguration) {
                createImageUrl(endpoint, backdropSizeParameter, backdropPath)
            },
            posterUrl = with(imageLoadingConfiguration) {
                createImageUrl(endpoint, posterSizeParameter, posterPath)
            },
            releaseDate = releaseDate,
            genres = genres.filter { it.id in genreIds }
        )
    }

    private fun createImageUrl(
        endpoint: URI,
        sizeParameter: ConfigurationDataSource.ConfigurationData.ImageSizeParameterData,
        path: URI
    ) = URI("$endpoint${sizeParameter.value}$path")

    private data class Output(
        override val title: String,
        override val originalTitle: String,
        override val overview: String,
        override val backdropUrl: URI,
        override val posterUrl: URI,
        override val releaseDate: LocalDate,
        override val genres: List<Genre>
    ) : MovieEntity
}
