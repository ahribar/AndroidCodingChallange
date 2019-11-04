package si.development.ahill.beeniusdemo.data.rest.services

import retrofit2.http.GET
import si.development.ahill.beeniusdemo.data.rest.models.UserRestModel

/**
 * Created by Andraž Hribar on 4. 11. 2019.
 * andraz.hribar@gmail.com
 */
interface UserRestService {

    @GET("/users")
    suspend fun getUsers(): List<UserRestModel>
}