package tenfen.rodolfo.moviesapp.features.home

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import tenfen.rodolfo.moviesapp.domain.Movie
import tenfen.rodolfo.moviesapp.domain.MovieRepository

class HomeViewModel(private val movieRepository: MovieRepository) : ViewModel() {

    private val _movies = MutableLiveData<List<Movie>>()
    val movies: LiveData<List<Movie>> get() = _movies

    init {
        viewModelScope.launch(Dispatchers.IO) {
            _movies.postValue(movieRepository.getPopularMovies())
        }
    }
}
