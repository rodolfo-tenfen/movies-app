package tenfen.rodolfo.feature.home

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import tenfen.rodolfo.domain.movie.usecase.MoviePort
import tenfen.rodolfo.movie.MovieModel

class HomeViewModel(private val moviePort: MoviePort) : ViewModel() {

    private val _movies = MutableLiveData<List<MovieModel>>()
    val movies: LiveData<List<MovieModel>> get() = _movies

    init {
        viewModelScope.launch(Dispatchers.IO) {
            _movies.postValue(moviePort.getPopularMovies().map(::MovieModel))
        }
    }
}
