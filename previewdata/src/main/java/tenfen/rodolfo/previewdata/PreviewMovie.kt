package tenfen.rodolfo.previewdata

import java.net.URI
import java.time.LocalDate
import tenfen.rodolfo.domain.movie.entity.Genre
import tenfen.rodolfo.domain.movie.entity.Movie

val genre1 = object : Genre {
    override val name = "horror"
}

val genre2 = object : Genre {
    override val name = "comedy"
}

val previewMovie =
    object : Movie {
        override val title = "Title"
        override val originalTitle = "Título"
        override val backdropUrl = URI("http://example.com/backdrop")
        override val posterUrl = URI("http://example.com/poster")
        override val overview =
            "This is the overview of the movie. It might be a very long string that will " +
                "need to be ellipsized: Lorem ipsum dolor sit amet, consectetur adipiscing elit. " +
                "Mauris magna nunc, fringilla interdum posuere in, tincidunt vel risus."
        override val releaseDate = LocalDate.now()
        override val genres = listOf(genre1, genre2)
    }
