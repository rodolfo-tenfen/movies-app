package tenfen.rodolfo.moviesapp.features.home

import androidx.lifecycle.ViewModel
import tenfen.rodolfo.moviesapp.domain.Movie

class MovieItemViewModel(val movie: Movie) : ViewModel() {

    val isOriginalTitleVisible get() = movie.title != movie.originalTitle
}
