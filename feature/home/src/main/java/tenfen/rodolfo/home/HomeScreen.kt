package tenfen.rodolfo.home

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
import tenfen.rodolfo.configuration.data.ImageLoadingConfigurationCache
import tenfen.rodolfo.configuration.data.ImageLoadingConfigurationFactory
import tenfen.rodolfo.movie.data.GenreCache
import tenfen.rodolfo.movie.data.GenreFactory
import tenfen.rodolfo.movie.data.MovieFactory
import tenfen.rodolfo.movie.domain.Movie
import tenfen.rodolfo.movieitem.previewMovie
import tenfen.rodolfo.repository.CachedGenreRepository
import tenfen.rodolfo.repository.CachedImageLoadingConfigurationRepository
import tenfen.rodolfo.repository.RemoteMovieRepository
import tenfen.rodolfo.tmdb.data.TmdbService

@Composable
private fun provideHomeViewModel(): HomeViewModel = viewModel {
    TmdbService.create(BuildConfig.TMDB_API_KEY)
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
            tenfen.rodolfo.movieitem.MovieItem(movie = it)
        }
    }
}
