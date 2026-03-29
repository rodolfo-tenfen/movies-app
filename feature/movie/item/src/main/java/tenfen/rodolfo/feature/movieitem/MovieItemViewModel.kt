package tenfen.rodolfo.feature.movieitem

import androidx.lifecycle.ViewModel
import tenfen.rodolfo.domain.movie.entity.Movie

class MovieItemViewModel(val movie: Movie) : ViewModel() {

    val isOriginalTitleVisible get() = movie.title != movie.originalTitle
}
