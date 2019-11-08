package si.development.ahill.beeniusdemo.data.repositories.photo.datasources

import si.development.ahill.beeniusdemo.data.models.PhotoEntity

/**
 * Created by Andraž Hribar on 8. 11. 2019.
 * andraz.hribar@gmail.com
 */
interface PhotoDataSource {

    suspend fun providePhotoById(photoId: Long): PhotoEntity?

    suspend fun providePhotoList(): List<PhotoEntity>

    suspend fun providePhotoListByAlbumId(albumId: Long): List<PhotoEntity>
}