package tenfen.rodolfo.movie.ui.details

import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import coil.compose.AsyncImage
import tenfen.rodolfo.movie.MovieModel
import tenfen.rodolfo.movie.ui.releasedate.ReleaseDate
import tenfen.rodolfo.previewdata.previewMovie
import tenfen.rodolfo.theme.MoviesAppTheme

class MovieDetailsActivity : ComponentActivity() {

    companion object {

        private const val MOVIE_ARGUMENT_KEY = "MOVIE_ARGUMENT_KEY"

        fun createIntent(context: Context, movie: MovieModel) =
            Intent(context, MovieDetailsActivity::class.java).apply {
                Bundle()
                    .apply { putParcelable(MOVIE_ARGUMENT_KEY, movie) }
                    .let { putExtras(it) }
            }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {
            @Suppress("DEPRECATION")
            MovieDetailsActivityScreen(
                movie = intent.getParcelableExtra(MOVIE_ARGUMENT_KEY)!!,
                navigateBack = ::finish
            )
        }
    }
}

@Composable
private fun provideMovieDetailsViewModel(movie: MovieModel) = viewModel {
    MovieDetailsViewModel(movie)
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MovieDetailsActivityScreen(
    movie: MovieModel,
    navigateBack: () -> Unit,
    viewModel: MovieDetailsViewModel = provideMovieDetailsViewModel(movie)
) {
    MoviesAppTheme {
        Scaffold(
            topBar = {
                TopAppBar(
                    title = { Text(viewModel.movie.title) },
                    navigationIcon = { BackButton(navigateBack) }
                )
            }
        ) { innerPadding ->
            Content(viewModel.movie, innerPadding)
        }
    }
}

@Composable
private fun BackButton(navigateBack: () -> Unit) {
    IconButton(onClick = navigateBack) {
        Icon(
            imageVector = Icons.AutoMirrored.Default.ArrowBack,
            contentDescription = stringResource(R.string.movie_details_back_button_description)
        )
    }
}

@Composable
private fun Content(movie: MovieModel, innerPadding: PaddingValues) {
    Column(
        Modifier
            .padding(innerPadding)
            .fillMaxHeight()
            .fillMaxWidth()
            .padding(8.dp),
        verticalArrangement = Arrangement.spacedBy(8.dp)
    ) {
        Row(
            Modifier
                .fillMaxWidth()
                .wrapContentHeight(),
            horizontalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            AsyncImage(
                model = movie.posterUrl.toString(),
                contentDescription = null,
                Modifier
                    .fillMaxWidth(fraction = 1 / 2f)
                    .wrapContentHeight(),
                contentScale = ContentScale.Fit,
                alignment = Alignment.Center
            )

            Column(verticalArrangement = Arrangement.spacedBy(8.dp)) {
                Text(
                    movie.title,
                    Modifier
                        .fillMaxWidth()
                        .wrapContentHeight(),
                    fontSize = 22.sp,
                    fontWeight = FontWeight.Bold
                )

                Text(
                    movie.originalTitle,
                    Modifier
                        .fillMaxWidth()
                        .wrapContentHeight(),
                    fontSize = 16.sp
                )

                ReleaseDate(movie, fontSize = 14.sp)
            }
        }

        Text(
            movie.overview,
            Modifier
                .fillMaxWidth()
                .wrapContentHeight(),
            fontSize = 14.sp
        )

        AsyncImage(
            model = movie.backdropUrl.toString(),
            contentDescription = null,
            Modifier
                .fillMaxWidth()
                .wrapContentHeight(),
            contentScale = ContentScale.FillWidth,
            alignment = Alignment.TopCenter
        )
    }
}

@Preview
@Composable
fun MovieDetailsScreenPreview() {
    MovieDetailsActivityScreen(movie = previewMovie, navigateBack = {})
}
