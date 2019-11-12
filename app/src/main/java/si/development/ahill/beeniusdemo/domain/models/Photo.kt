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
) {

    override fun equals(other: Any?): Boolean =
        (other as? Photo)?.let { otherPhoto ->
            albumId == otherPhoto.albumId
                    && thumbnailUrl == otherPhoto.thumbnailUrl
                    && title == otherPhoto.title
                    && url == otherPhoto.url
        } ?: false

    override fun hashCode(): Int {
        var result = albumId?.hashCode() ?: 0
        result = 31 * result + (thumbnailUrl?.hashCode() ?: 0)
        result = 31 * result + (title?.hashCode() ?: 0)
        result = 31 * result + (url?.hashCode() ?: 0)
        return result
    }
}