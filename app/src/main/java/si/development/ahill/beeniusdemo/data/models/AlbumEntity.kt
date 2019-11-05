package si.development.ahill.beeniusdemo.data.models

/**
 * Created by Andraž Hribar on 5. 11. 2019.
 * andraz.hribar@gmail.com
 */
data class AlbumEntity(
    val id: Long = 0L,
    val title: String?,
    val userId: Long?
)