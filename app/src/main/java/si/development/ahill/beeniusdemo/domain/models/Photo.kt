package si.development.ahill.beeniusdemo.domain.models

/**
 * Created by Andraž Hribar on 5. 11. 2019.
 * andraz.hribar@gmail.com
 */
data class Photo(
    val id: Long?,
    val albumId: Long?,
    val thumbnailUrl: String?,
    val title: String?,
    val url: String?
)