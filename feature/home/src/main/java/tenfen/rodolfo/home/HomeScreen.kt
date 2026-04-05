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
import tenfen.rodolfo.data.tmdb.configuration.datasource.memory.ConfigurationCache
import tenfen.rodolfo.data.tmdb.configuration.datasource.remote.ConfigurationRetrofitDataSource
import tenfen.rodolfo.data.tmdb.configuration.datasource.remote.factory.ConfigurationFactory
import tenfen.rodolfo.data.tmdb.configuration.datasource.remote.service.ConfigurationRetrofitService
import tenfen.rodolfo.data.tmdb.movie.datasource.memory.GenreCache
import tenfen.rodolfo.data.tmdb.movie.datasource.remote.GenreRetrofitDataSource
import tenfen.rodolfo.data.tmdb.movie.datasource.remote.MovieRetrofitDataSource
import tenfen.rodolfo.data.tmdb.movie.datasource.remote.factory.MovieFactory as MovieDataFactory
import tenfen.rodolfo.data.tmdb.movie.datasource.remote.service.GenreRetrofitService
import tenfen.rodolfo.data.tmdb.movie.datasource.remote.service.MovieRetrofitService
import tenfen.rodolfo.domain.movie.entity.Movie
import tenfen.rodolfo.home.BuildConfig
import tenfen.rodolfo.movie.ui.item.MovieItem
import tenfen.rodolfo.previewdata.previewMovie
import tenfen.rodolfo.repository.MovieRepository
import tenfen.rodolfo.repository.factory.MovieFactory as MovieEntityFactory
import tenfen.rodolfo.repository.store.CacheFirstConfigurationStore
import tenfen.rodolfo.repository.store.CacheFirstGenreStore
import tenfen.rodolfo.tmdb.data.TmdbFactory

@Composable
private fun provideHomeViewModel(): HomeViewModel = viewModel {
    val configurationRemoteDataSource = ConfigurationRetrofitService::class.java
        .let { TmdbFactory.create(BuildConfig.TMDB_API_KEY, it) }
        .let { ConfigurationRetrofitDataSource(it, ConfigurationFactory()::create) }
    val genreRemoteDataSource = GenreRetrofitService::class.java
        .let { TmdbFactory.create(BuildConfig.TMDB_API_KEY, it) }
        .let(::GenreRetrofitDataSource)
    val dateTimeFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd")
    MovieRetrofitService::class.java
        .let { TmdbFactory.create(BuildConfig.TMDB_API_KEY, clazz = it) }
        .let {
            MovieRetrofitDataSource(movieService = it, MovieDataFactory(dateTimeFormatter)::create)
        }
        .let {
            MovieRepository(
                CacheFirstConfigurationStore(configurationRemoteDataSource, ConfigurationCache),
                CacheFirstGenreStore(genreRemoteDataSource, GenreCache),
                movieDataSource = it,
                MovieEntityFactory()::create
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
