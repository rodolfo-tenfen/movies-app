package tenfen.rodolfo.previewdata

import java.net.URI
import java.time.LocalDate
import tenfen.rodolfo.movie.GenreModel
import tenfen.rodolfo.movie.MovieModel

val genre1 = GenreModel(name = "horror")

val genre2 = GenreModel(name = "comedy")

val previewMovie =
    MovieModel(
        title = "Title",
        originalTitle = "Título",
        backdropUrl = URI("http://example.com/backdrop"),
        posterUrl = URI("http://example.com/poster"),
        overview =
            "This is the overview of the movie. It might be a very long string that will " +
                "need to be ellipsized: Lorem ipsum dolor sit amet, consectetur adipiscing elit. " +
                "Mauris magna nunc, fringilla interdum posuere in, tincidunt vel risus.",
        releaseDate = LocalDate.now(),
        genres = listOf(genre1, genre2)
    )
