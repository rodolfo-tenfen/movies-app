package tenfen.rodolfo.repository

import tenfen.rodolfo.domain.movie.usecase.GenrePort
import tenfen.rodolfo.repository.store.GenreStore

class GenreRepository(private val genreStore: GenreStore) : GenrePort {

    override suspend fun getGenres() = genreStore.getGenres()
}
