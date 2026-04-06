package tenfen.rodolfo.movie.ui.item

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.RowScope
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
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
import tenfen.rodolfo.movie.MovieModel
import tenfen.rodolfo.movie.ui.releasedate.ReleaseDate
import tenfen.rodolfo.previewdata.previewMovie
import tenfen.rodolfo.theme.onSecondary
import tenfen.rodolfo.theme.outline

@Composable
fun MovieItem(movie: MovieModel, modifier: Modifier = Modifier) {
    Card(
        modifier
            .padding(horizontal = 8.dp, vertical = 4.dp)
            .wrapContentHeight()
            .fillMaxWidth(),
        elevation = CardDefaults.cardElevation(2.dp),
        border = BorderStroke(1.dp, outline),
        colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.surface)
    ) {
        Row(modifier = Modifier.fillMaxSize(), verticalAlignment = Alignment.Top) {
            PosterImage(movie)

            Column(
                Modifier
                    .fillMaxHeight()
                    .padding(vertical = 8.dp)
                    .padding(end = 8.dp)
            ) {
                Title(movie)

                Row(
                    Modifier
                        .fillMaxWidth()
                        .wrapContentHeight()
                        .padding(bottom = 8.dp),
                    verticalAlignment = Alignment.Bottom
                ) {
                    if (movie.title != movie.originalTitle)
                        OriginalTitle(movie)

                    ReleaseDate(movie, color = onSecondary, maxLines = 1)
                }

                Text(
                    text = movie.overview,
                    Modifier.padding(bottom = 8.dp),
                    overflow = TextOverflow.Ellipsis,
                    maxLines = 3
                )
            }
        }
    }
}

@Composable
private fun PosterImage(movie: MovieModel) {
    AsyncImage(
        model = movie.posterUrl.toString(),
        contentDescription = null,
        Modifier
            .padding(vertical = 8.dp, horizontal = 8.dp)
            .fillMaxWidth(fraction = 3 / 10f)
            .fillMaxHeight(),
        contentScale = ContentScale.FillWidth,
        alignment = Alignment.TopCenter,
        error = painterResource(id = R.drawable.home_loading_error)
    )
}

@Composable
private fun Title(movie: MovieModel) {
    Text(
        text = movie.title,
        Modifier
            .fillMaxWidth()
            .wrapContentHeight(),
        overflow = TextOverflow.Ellipsis,
        maxLines = 1,
        fontSize = 18.sp,
        fontWeight = FontWeight.Bold
    )
}

@Composable
private fun RowScope.OriginalTitle(movie: MovieModel) {
    Text(
        text = movie.originalTitle,
        Modifier
            .weight(1f, fill = false)
            .padding(end = 8.dp),
        overflow = TextOverflow.Ellipsis,
        maxLines = 1,
        fontWeight = FontWeight.Bold
    )
}

@Preview
@Composable
private fun MovieItemPreview() {
    MovieItem(previewMovie)
}
