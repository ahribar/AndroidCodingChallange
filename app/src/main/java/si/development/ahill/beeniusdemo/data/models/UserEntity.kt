package si.development.ahill.beeniusdemo.data.models

/**
 * Created by Andraž Hribar on 5. 11. 2019.
 * andraz.hribar@gmail.com
 */
data class UserEntity(
    val id: Long,
    val email: String?,
    val name: String?,
    val phone: String?,
    val username: String?,
    val website: String?,
    val city: String?,
    val latitude: String?,
    val longitude: String?,
    val street: String?,
    val suite: String?,
    val zipCode: String?,
    val companyBs: String?,
    val companyCatchPhrase: String?,
    val companyName: String?
)