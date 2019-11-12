package si.development.ahill.beeniusdemo.domain.models

/**
 * Created by Andraž Hribar on 5. 11. 2019.
 * andraz.hribar@gmail.com
 */
data class User(
    val id: Long,
    val username: String?,
    val name: String?
) {

    override fun equals(other: Any?): Boolean =
        (other as? User)?.let { otherUser ->
            id == otherUser.id
                    && username == otherUser.username
                    && name == otherUser.name
        } ?: false

    override fun hashCode(): Int {
        var result = id.hashCode()
        result = 31 * result + (username?.hashCode() ?: 0)
        result = 31 * result + (name?.hashCode() ?: 0)
        return result
    }
}