package tenfen.rodolfo.movieitem

import androidx.lifecycle.ViewModel
import tenfen.rodolfo.movie.domain.Movie

class MovieItemViewModel(val movie: Movie) : ViewModel() {

    val isOriginalTitleVisible get() = movie.title != movie.originalTitle
}
