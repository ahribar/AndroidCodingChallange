package si.development.ahill.beeniusdemo.data.rest.models

data class UserRestModel(
    val address: Address?,
    val company: Company?,
    val email: String?,
    val id: Long?,
    val name: String?,
    val phone: String?,
    val username: String?,
    val website: String?
) {
    data class Address(
        val city: String?,
        val geo: Geo?,
        val street: String?,
        val suite: String?,
        val zipcode: String?
    ) {
        data class Geo(
            val lat: String?,
            val lng: String?
        )
    }

    data class Company(
        val bs: String?,
        val catchPhrase: String?,
        val name: String?
    )
}