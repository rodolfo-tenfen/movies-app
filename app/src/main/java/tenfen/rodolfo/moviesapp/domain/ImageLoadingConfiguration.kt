package tenfen.rodolfo.moviesapp.domain

import java.net.URL

data class ImageLoadingConfiguration(
    val endpoint: URL,
    val posterSizeParameter: ImageSizeParameter,
    val backdropSizeParameter: ImageSizeParameter
)
