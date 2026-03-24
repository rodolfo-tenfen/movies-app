package tenfen.rodolfo.feature.moviedetails

import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.LargeTopAppBar
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.lifecycle.viewmodel.compose.viewModel
import coil.compose.AsyncImage
import tenfen.rodolfo.domain.movie.entity.Movie
import tenfen.rodolfo.moviedetails.R
import tenfen.rodolfo.previewdata.previewMovie
import tenfen.rodolfo.theme.MoviesAppTheme

class MovieDetailsActivity : ComponentActivity() {

    companion object {

        private const val MOVIE_ARGUMENT_KEY = "MOVIE_ARGUMENT_KEY"

        fun createIntent(context: Context, movie: Movie) =
            Intent(context, MovieDetailsActivity::class.java).apply {
                Bundle()
                    .apply { putSerializable(MOVIE_ARGUMENT_KEY, movie) }
                    .let { putExtras(it) }
            }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {
            @Suppress("DEPRECATION")
            MovieDetailsActivityContent(movie = intent.getParcelableExtra(MOVIE_ARGUMENT_KEY)!!)
        }
    }
}

@Composable
private fun provideMovieDetailsViewModel(movie: Movie) = viewModel {
    MovieDetailsViewModel(movie)
}

@OptIn(ExperimentalMaterial3Api::class)
@Preview
@Composable
fun MovieDetailsActivityContent(
    movie: Movie = previewMovie,
    viewModel: MovieDetailsViewModel = provideMovieDetailsViewModel(movie)
) {
    MoviesAppTheme {
        Scaffold(
            topBar = {
                LargeTopAppBar(
                    title = { Text(text = movie.title) },
                    navigationIcon = { BackButton() }
                )
                // AsyncImage(
                //     model = viewModel.movie.backdropUrl,
                //     contentDescription = null
                // )
            }
        ) { _ ->
            AsyncImage(
                model = viewModel.movie.backdropUrl,
                contentDescription = null
            )
        }
    }
}

@Composable
private fun BackButton() {
    IconButton(onClick = { }) {
        Icon(
            imageVector = Icons.AutoMirrored.Default.ArrowBack,
            contentDescription = stringResource(R.string.movie_details_back_button_description)
        )
    }
}
