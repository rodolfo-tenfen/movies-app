package tenfen.rodolfo.moviesapp.data

import java.net.URL
import java.time.LocalDate
import java.time.format.DateTimeFormatter
import tenfen.rodolfo.moviesapp.domain.Genre
import tenfen.rodolfo.moviesapp.domain.ImageLoadingConfiguration
import tenfen.rodolfo.moviesapp.domain.ImageSizeParameter
import tenfen.rodolfo.moviesapp.domain.Movie

class MovieFactory(private val dateFormatter: DateTimeFormatter) {

    fun create(
        input: MovieResponse,
        genres: List<Genre>,
        imageLoadingConfiguration: ImageLoadingConfiguration
    ) = with(input) {
        Movie(
            title = title,
            originalTitle = originalTitle,
            overview = overview,
            backdropUrl = with(imageLoadingConfiguration) {
                createImageUrl(endpoint, backdropSizeParameter, backdropPath)
            },
            posterUrl = with(imageLoadingConfiguration) {
                createImageUrl(endpoint, posterSizeParameter, posterPath)
            },
            releaseDate = LocalDate.parse(releaseDate, dateFormatter),
            genres = genres.filter { it.id in genreIds }
        )
    }

    private fun createImageUrl(endpoint: URL, sizeParameter: ImageSizeParameter, path: String) =
        URL("$endpoint${sizeParameter.value}$path")
}
