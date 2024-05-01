package tenfen.rodolfo.moviesapp.ui.theme

import android.app.Activity
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.ProvideTextStyle
import androidx.compose.material3.darkColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.SideEffect
import androidx.compose.ui.graphics.toArgb
import androidx.compose.ui.platform.LocalView
import androidx.compose.ui.text.TextStyle
import androidx.core.view.WindowCompat

private val moviesColorScheme =
    darkColorScheme(
        primary = primary,
        secondary = secondary,
        tertiary = tertiary,
        background = screenBackground,
        surface = listItemBackground
    )

@Composable
fun MoviesAppTheme(content: @Composable () -> Unit) {
    val view = LocalView.current
    if (!view.isInEditMode) {
        SideEffect {
            val window = (view.context as Activity).window
            window.statusBarColor = moviesColorScheme.primary.toArgb()
            WindowCompat.getInsetsController(window, view).isAppearanceLightStatusBars = false
        }
    }

    MaterialTheme(
        colorScheme = moviesColorScheme,
        typography = Typography,
        content = {
            ProvideTextStyle(value = TextStyle(color = primaryText), content = content)
        }
    )
}
