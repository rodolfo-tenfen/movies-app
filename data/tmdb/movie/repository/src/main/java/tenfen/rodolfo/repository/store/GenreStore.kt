package tenfen.rodolfo.repository.store

import tenfen.rodolfo.repository.port.GenreDataSource

interface GenreStore {

    suspend fun getGenres(): List<GenreDataSource.GenreData>
}

class CacheFirstGenreStore(
    private val remoteDataSource: GenreDataSource.Remote,
    private val memoryDataSource: GenreDataSource.Memory
) : GenreStore {

    override suspend fun getGenres() =
        memoryDataSource.genres
            .ifEmpty {
                remoteDataSource.getGenres()
                    .also { memoryDataSource.genres = it }
            }
}
