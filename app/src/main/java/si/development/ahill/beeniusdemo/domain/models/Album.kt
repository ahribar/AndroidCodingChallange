package si.development.ahill.beeniusdemo.domain.models

/**
 * Created by Andraž Hribar on 5. 11. 2019.
 * andraz.hribar@gmail.com
 */
data class Album(
    val id: Long,
    val title: String?,
    val userId: Long?,
    val thumbnailUrl: String? = null
)