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
) {

    override fun equals(other: Any?): Boolean =
        (other as? Album)?.let { otherAlbum ->
            id == otherAlbum.id
                    && title == otherAlbum.title
                    && userId == otherAlbum.userId
                    && thumbnailUrl == otherAlbum.thumbnailUrl
        } ?: false

    override fun hashCode(): Int {
        var result = id.hashCode()
        result = 31 * result + (title?.hashCode() ?: 0)
        result = 31 * result + (userId?.hashCode() ?: 0)
        result = 31 * result + (thumbnailUrl?.hashCode() ?: 0)
        return result
    }
}