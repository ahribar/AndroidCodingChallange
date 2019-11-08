package si.development.ahill.beeniusdemo.domain.repositories

import si.development.ahill.beeniusdemo.domain.models.Album

/**
 * Created by Andraž Hribar on 5. 11. 2019.
 * andraz.hribar@gmail.com
 */
interface AlbumRepository {

    suspend fun getAlbumsByUserId(userId: Long): List<Album>

    suspend fun getAlbumById(albumId: Long): Album?
}