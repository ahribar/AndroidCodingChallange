package si.development.ahill.beeniusdemo.data.repositories.photo.datasources

import si.development.ahill.beeniusdemo.data.mappers.PhotoMapper
import si.development.ahill.beeniusdemo.data.models.PhotoEntity
import si.development.ahill.beeniusdemo.data.repositories.datasources.RemoteDataSource
import si.development.ahill.beeniusdemo.data.rest.services.PhotoRestService
import javax.inject.Inject

/**
 * Created by Andraž Hribar on 8. 11. 2019.
 * andraz.hribar@gmail.com
 */
class PhotoDataSourceRemote @Inject constructor(
    private val restService: PhotoRestService,
    private val mapper: PhotoMapper
) : PhotoDataSource, RemoteDataSource<PhotoEntity> {

    //region PhotoDataSource

    override suspend fun providePhotoById(photoId: Long): PhotoEntity? =
        restService.getPhotos()
            .find { photoId == it.id }
            ?.let { mapper.mapAsEntity(it) }

    override suspend fun providePhotoList(): List<PhotoEntity> =
        restService.getPhotos().map { mapper.mapAsEntity(it) }

    override suspend fun providePhotoListByAlbumId(albumId: Long): List<PhotoEntity> =
        restService.getPhotos()
            .filter { albumId == it.albumId }
            .map { mapper.mapAsEntity(it) }

    //endregion PhotoDataSource
}