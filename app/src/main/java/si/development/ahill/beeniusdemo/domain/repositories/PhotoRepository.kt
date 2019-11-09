package si.development.ahill.beeniusdemo.domain.repositories

import si.development.ahill.beeniusdemo.domain.models.Photo

/**
 * Created by Andraž Hribar on 5. 11. 2019.
 * andraz.hribar@gmail.com
 */
interface PhotoRepository {

    suspend fun getPhotos(): List<Photo>

    suspend fun getPhotosByAlbumId(albumId: Long): List<Photo>

    suspend fun getPhotoById(photoId: Long): Photo?
}