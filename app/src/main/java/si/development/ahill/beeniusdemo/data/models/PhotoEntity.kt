package si.development.ahill.beeniusdemo.data.models

/**
 * Created by Andraž Hribar on 5. 11. 2019.
 * andraz.hribar@gmail.com
 */
data class PhotoEntity(
    val id: Long,
    val albumId: Long?,
    val thumbnailUrl: String?,
    val title: String?,
    val url: String?
)