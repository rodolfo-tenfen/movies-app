package tenfen.rodolfo.moviesapp.features.home

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.RowScope
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.sizeIn
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage
import java.net.URL
import java.time.LocalDate
import java.time.format.DateTimeFormatter
import tenfen.rodolfo.moviesapp.R
import tenfen.rodolfo.moviesapp.domain.Genre
import tenfen.rodolfo.moviesapp.domain.Movie
import tenfen.rodolfo.moviesapp.ui.theme.listItemBorder
import tenfen.rodolfo.moviesapp.ui.theme.secondaryText

@Composable
private fun provideMovieItemViewModel(movie: Movie) = MovieItemViewModel(movie)

val previewMovie =
    Movie(
        title = "Title",
        originalTitle = "Título",
        backdropUrl = URL("http://example.com/backdrop"),
        posterUrl = URL("http://example.com/poster"),
        overview = "This is the overview of the movie. It might be a very long string that will " +
            "need to be ellipsized: Lorem ipsum dolor sit amet, consectetur adipiscing elit. " +
            "Mauris magna nunc, fringilla interdum posuere in, tincidunt vel risus.",
        releaseDate = LocalDate.now(),
        genres = listOf(Genre(id = 1, "horror"), Genre(id = 2, "comedy"))
    )

@Preview
@Composable
fun MovieItem(movie: Movie = previewMovie) {
    val viewModel = provideMovieItemViewModel(movie)

    Card(
        Modifier.padding(horizontal = 8.dp, vertical = 4.dp).wrapContentHeight().fillMaxWidth(),
        elevation = CardDefaults.cardElevation(2.dp),
        border = BorderStroke(1.dp, listItemBorder),
        colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.surface)
    ) {
        Row(modifier = Modifier.fillMaxSize(), verticalAlignment = Alignment.Top) {
            PosterImage(viewModel)

            Column(Modifier.fillMaxHeight().padding(vertical = 8.dp).padding(end = 8.dp)) {
                Title(viewModel)

                Row(
                    Modifier.fillMaxWidth().wrapContentHeight().padding(bottom = 8.dp),
                    verticalAlignment = Alignment.Bottom
                ) {
                    OriginalTitle(viewModel)

                    ReleaseDate(viewModel)
                }

                Text(
                    text = viewModel.movie.overview,
                    Modifier.padding(bottom = 8.dp),
                    overflow = TextOverflow.Ellipsis,
                    maxLines = 3
                )
            }
        }
    }
}

@Composable
private fun PosterImage(viewModel: MovieItemViewModel) {
    AsyncImage(
        model = viewModel.movie.posterUrl.toString(),
        contentDescription = null,
        Modifier
            .padding(vertical = 8.dp, horizontal = 8.dp)
            .sizeIn(minWidth = 99.dp, maxWidth = 99.dp)
            .fillMaxHeight(),
        contentScale = ContentScale.FillWidth,
        alignment = Alignment.TopCenter,
        error = painterResource(id = R.drawable.home_loading_error)
    )
}

@Composable
private fun Title(viewModel: MovieItemViewModel) {
    Text(
        text = viewModel.movie.title,
        Modifier.fillMaxWidth().wrapContentHeight().padding(bottom = 8.dp),
        overflow = TextOverflow.Ellipsis,
        maxLines = 1,
        fontSize = 18.sp,
        fontWeight = FontWeight.Bold
    )
}

@Composable
private fun RowScope.OriginalTitle(viewModel: MovieItemViewModel) {
    if (viewModel.isOriginalTitleVisible) {
        Text(
            text = viewModel.movie.originalTitle,
            Modifier.weight(1f, fill = false).padding(end = 8.dp),
            overflow = TextOverflow.Ellipsis,
            maxLines = 1,
            fontWeight = FontWeight.Bold
        )
    }
}

@Composable
private fun ReleaseDate(viewModel: MovieItemViewModel) {
    val dateFormatter by remember { mutableStateOf(DateTimeFormatter.ofPattern("MMMM dd, yyyy")) }

    Text(
        text = dateFormatter.format(viewModel.movie.releaseDate),
        color = secondaryText,
        fontSize = 12.sp,
        maxLines = 1
    )
}
