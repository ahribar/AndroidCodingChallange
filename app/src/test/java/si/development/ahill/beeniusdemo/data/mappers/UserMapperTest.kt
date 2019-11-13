package si.development.ahill.beeniusdemo.data.mappers

import org.hamcrest.CoreMatchers.`is`
import org.hamcrest.CoreMatchers.instanceOf
import org.hamcrest.MatcherAssert.assertThat
import org.junit.After
import org.junit.Before
import org.junit.Test
import si.development.ahill.beeniusdemo.data.models.UserEntity
import si.development.ahill.beeniusdemo.data.rest.models.UserRestModel
import si.development.ahill.beeniusdemo.domain.models.User

/**
 * Created by Andraž Hribar on 12. 11. 2019.
 * andraz.hribar@gmail.com
 */
class UserMapperTest {

    private var mapper: UserMapper? = null

    @Before
    fun setUp() {
        mapper = UserMapper()
    }

    @After
    fun tearDown() {
        mapper = null
    }

    @Test
    fun testMapAsEntity() {
        val userEntity = mapper?.mapAsEntity(createMockUserResponse())
        assert(userEntity != null)
        assertThat(userEntity!!, instanceOf(UserEntity::class.java))
        assertThat(userEntity.id, `is`(MOCK_ID))
        assertThat(userEntity.email, `is`(MOCK_EMAIL))
        assertThat(userEntity.name, `is`(MOCK_NAME))
        assertThat(userEntity.phone, `is`(MOCK_PHONE))
        assertThat(userEntity.username, `is`(MOCK_USERNAME))
        assertThat(userEntity.website, `is`(MOCK_WEBSITE))
        assertThat(userEntity.city, `is`(MOCK_CITY))
        assertThat(userEntity.latitude, `is`(MOCK_LATITUDE))
        assertThat(userEntity.longitude, `is`(MOCK_LONGITUDE))
        assertThat(userEntity.street, `is`(MOCK_STREET))
        assertThat(userEntity.suite, `is`(MOCK_SUITE))
        assertThat(userEntity.zipCode, `is`(MOCK_ZIPCODE))
        assertThat(userEntity.companyBs, `is`(MOCK_COMPANY_BS))
        assertThat(userEntity.companyCatchPhrase, `is`(MOCK_COMPANY_CATCHPHRASE))
        assertThat(userEntity.companyName, `is`(MOCK_COMPANY_NAME))
    }

    @Test
    fun testMapAsPresentable() {
        val user = mapper?.mapAsPresentable(createMockUserEntity())
        assert(user != null)
        assertThat(user!!, instanceOf(User::class.java))
        assertThat(user.id, `is`(MOCK_ID))
        assertThat(user.name, `is`(MOCK_NAME))
        assertThat(user.username, `is`(MOCK_USERNAME))
    }

    private fun createMockUserResponse(): UserRestModel =
        UserRestModel(
            address = UserRestModel.Address(
                city = MOCK_CITY,
                geo = UserRestModel.Address.Geo(
                    lat = MOCK_LATITUDE,
                    lng = MOCK_LONGITUDE
                ),
                street = MOCK_STREET,
                suite = MOCK_SUITE,
                zipcode = MOCK_ZIPCODE
            ),
            company = UserRestModel.Company(
                bs = MOCK_COMPANY_BS,
                catchPhrase = MOCK_COMPANY_CATCHPHRASE,
                name = MOCK_COMPANY_NAME
            ),
            email = MOCK_EMAIL,
            id = MOCK_ID,
            name = MOCK_NAME,
            phone = MOCK_PHONE,
            username = MOCK_USERNAME,
            website = MOCK_WEBSITE
        )

    private fun createMockUserEntity(): UserEntity =
        UserEntity(
            id = MOCK_ID,
            email = MOCK_EMAIL,
            name = MOCK_NAME,
            phone = MOCK_PHONE,
            username = MOCK_USERNAME,
            website = MOCK_WEBSITE,
            city = MOCK_CITY,
            latitude = MOCK_LATITUDE,
            longitude = MOCK_LONGITUDE,
            street = MOCK_STREET,
            suite = MOCK_SUITE,
            zipCode = MOCK_ZIPCODE,
            companyBs = MOCK_COMPANY_BS,
            companyCatchPhrase = MOCK_COMPANY_CATCHPHRASE,
            companyName = MOCK_COMPANY_NAME
        )

    companion object {

        const val MOCK_ID: Long = 69
        const val MOCK_EMAIL: String = "user@mock.com"
        const val MOCK_NAME: String = "Đony Novak"
        const val MOCK_PHONE: String = "041XXXXXX"
        const val MOCK_USERNAME: String = "nr1"
        const val MOCK_WEBSITE: String = "www.gianni.com"
        const val MOCK_CITY: String = "Ljubljana"
        const val MOCK_LATITUDE: String = "46.056946"
        const val MOCK_LONGITUDE: String = "14.505751"
        const val MOCK_STREET: String = "Slovenska cesta 55"
        const val MOCK_SUITE: String = "1"
        const val MOCK_ZIPCODE: String = "1000"
        const val MOCK_COMPANY_BS: String = "Crypto investing"
        const val MOCK_COMPANY_CATCHPHRASE: String = "Invest in your Future Self"
        const val MOCK_COMPANY_NAME: String = "ICONOMI"
    }
}