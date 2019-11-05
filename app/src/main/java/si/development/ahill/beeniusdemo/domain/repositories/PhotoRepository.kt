package si.development.ahill.beeniusdemo.domain.repositories

import si.development.ahill.beeniusdemo.domain.models.Photo

/**
 * Created by Andraž Hribar on 5. 11. 2019.
 * andraz.hribar@gmail.com
 */
interface PhotoRepository {

    fun getPhotos(): List<Photo>

    fun getPhotosByAlbumId(albumId: Long): List<Photo>

    fun getPhotoById(photoId: Long): Photo?
}