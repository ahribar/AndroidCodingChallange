package si.development.ahill.beeniusdemo.data.rest.services

import retrofit2.http.GET
import si.development.ahill.beeniusdemo.data.rest.models.AlbumRestModel

/**
 * Created by Andraž Hribar on 4. 11. 2019.
 * andraz.hribar@gmail.com
 */
interface AlbumRestService {

    @GET("/albums")
    suspend fun getAlbums(): List<AlbumRestModel>
}