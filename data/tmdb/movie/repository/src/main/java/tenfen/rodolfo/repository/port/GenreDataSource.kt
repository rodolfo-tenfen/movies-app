package tenfen.rodolfo.repository.port

import tenfen.rodolfo.domain.movie.entity.Genre as GenreEntity

interface GenreDataSource {

    interface GenreData : GenreEntity {
        val id: Int
    }

    interface Remote {

        suspend fun getGenres(): List<GenreData>
    }

    interface Memory {
        var genres: List<GenreData>
    }
}
