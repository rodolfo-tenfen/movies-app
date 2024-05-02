package tenfen.rodolfo.moviesapp.features.home

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.lifecycle.viewmodel.compose.viewModel
import java.time.format.DateTimeFormatter
import tenfen.rodolfo.moviesapp.data.GenreFactory
import tenfen.rodolfo.moviesapp.data.ImageLoadingConfigurationFactory
import tenfen.rodolfo.moviesapp.data.MovieFactory
import tenfen.rodolfo.moviesapp.data.TmdbService
import tenfen.rodolfo.moviesapp.domain.CachedGenreRepository
import tenfen.rodolfo.moviesapp.domain.CachedImageLoadingConfigurationRepository
import tenfen.rodolfo.moviesapp.domain.GenreCache
import tenfen.rodolfo.moviesapp.domain.ImageLoadingConfigurationCache
import tenfen.rodolfo.moviesapp.domain.Movie
import tenfen.rodolfo.moviesapp.domain.RemoteMovieRepository

@Composable
private fun provideHomeViewModel(): HomeViewModel = viewModel {
    TmdbService.create()
        .let { tmdbService ->
            RemoteMovieRepository(
                tmdbService,
                CachedGenreRepository(
                    tmdbService,
                    GenreCache,
                    genreFactory = GenreFactory()::create
                ),
                CachedImageLoadingConfigurationRepository(
                    tmdbService,
                    ImageLoadingConfigurationCache,
                    imageLoadingConfigurationFactory = ImageLoadingConfigurationFactory()::create
                ),
                movieFactory = MovieFactory(DateTimeFormatter.ofPattern("yyyy-MM-dd"))::create
            )
        }
        .let(::HomeViewModel)
}

@Preview
@Composable
fun HomeScreen(viewModel: HomeViewModel = provideHomeViewModel()) {
    val movies by viewModel.movies.observeAsState(initial = emptyList())

    PopularMovies(movies)
}

@Preview
@Composable
fun PopularMovies(movies: List<Movie> = listOf(previewMovie)) {
    LazyColumn(modifier = Modifier.fillMaxSize()) {
        items(items = movies) {
            MovieItem(movie = it)
        }
    }
}
