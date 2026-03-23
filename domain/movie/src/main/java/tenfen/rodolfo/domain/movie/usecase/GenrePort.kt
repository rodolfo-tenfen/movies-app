package tenfen.rodolfo.domain.movie.usecase

import tenfen.rodolfo.domain.movie.entity.Genre

interface GenrePort {

    suspend fun getGenres(): List<Genre>
}
