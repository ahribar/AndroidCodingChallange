package si.development.ahill.beeniusdemo.data.repositories.photo

import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import si.development.ahill.beeniusdemo.data.models.PhotoEntity
import si.development.ahill.beeniusdemo.data.repositories.photo.datasources.PhotoDataSource
import si.development.ahill.beeniusdemo.data.repositories.photo.datasources.PhotoDataSourceLocal
import si.development.ahill.beeniusdemo.data.repositories.photo.datasources.PhotoDataSourceRemote
import si.development.ahill.beeniusdemo.domain.mappers.DomainModelMapper
import si.development.ahill.beeniusdemo.domain.models.Photo
import si.development.ahill.beeniusdemo.domain.repositories.PhotoRepository
import javax.inject.Inject

/**
 * Created by Andraž Hribar on 5. 11. 2019.
 * andraz.hribar@gmail.com
 */
class PhotoDataRepository @Inject constructor(
    private val localDataSourceLocal: PhotoDataSourceLocal,
    private val remoteDataSource: PhotoDataSourceRemote,
    private val mapper: DomainModelMapper<PhotoEntity, Photo>
) : PhotoRepository {

    override suspend fun getPhotos(): List<Photo> =
        selectDataSource().providePhotoList()
            .also { syncLocalIfNecessary(it) }
            .map { mapper.mapAsPresentable(it) }

    override suspend fun getPhotosByAlbumId(albumId: Long): List<Photo> =
        selectDataSource().providePhotoListByAlbumId(albumId)
            .also { syncLocalIfNecessary(it) }
            .map { mapper.mapAsPresentable(it) }

    override suspend fun getPhotoById(photoId: Long): Photo? =
        selectDataSource().providePhotoById(photoId)?.let { mapper.mapAsPresentable(it) }

    private fun selectDataSource(): PhotoDataSource =
        if (localDataSourceLocal.isExpired()) remoteDataSource else localDataSourceLocal

    private fun syncLocalIfNecessary(users: List<PhotoEntity>) {
        if (localDataSourceLocal.isExpired()) {
            CoroutineScope(Dispatchers.IO).launch {
                localDataSourceLocal.syncData(users)
            }
        }
    }
}