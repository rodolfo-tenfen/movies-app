package tenfen.rodolfo.moviesapp.data

import tenfen.rodolfo.moviesapp.domain.Genre

class GenreFactory {

    fun create(input: GenreResponse) = Genre(id = input.id, name = input.name)
}
