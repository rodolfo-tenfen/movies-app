package tenfen.rodolfo.movie.data

import tenfen.rodolfo.movie.domain.Genre

class GenreFactory {

    fun create(input: GenreResponse) = Genre(id = input.id, name = input.name)
}
