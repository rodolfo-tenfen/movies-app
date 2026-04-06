package tenfen.rodolfo.movie.ui.releasedate

import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.sp
import java.time.format.DateTimeFormatter
import tenfen.rodolfo.movie.MovieModel
import tenfen.rodolfo.previewdata.previewMovie

@Composable
fun ReleaseDate(
    movie: MovieModel,
    color: Color = Color.Unspecified,
    fontSize: TextUnit = 12.sp,
    maxLines: Int = Int.MAX_VALUE
) {
    val dateFormatter by remember { mutableStateOf(DateTimeFormatter.ofPattern("MMMM dd, yyyy")) }

    Text(
        text = dateFormatter.format(movie.releaseDate),
        color = color,
        fontSize = fontSize,
        maxLines = maxLines
    )
}

@Composable
@Preview
private fun ReleaseDatePreview() {
    ReleaseDate(movie = previewMovie)
}
