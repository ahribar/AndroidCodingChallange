package si.development.ahill.beeniusdemo.data.rest.services

import retrofit2.http.GET
import si.development.ahill.beeniusdemo.data.rest.models.PhotoRestModel

/**
 * Created by Andraž Hribar on 4. 11. 2019.
 * andraz.hribar@gmail.com
 */
interface PhotoRestService {

    @GET("/photos")
    suspend fun getPhotos(): List<PhotoRestModel>
}