package si.development.ahill.beeniusdemo.data.repositories.photo.datasources

import si.development.ahill.beeniusdemo.data.database.daos.PhotoDao
import si.development.ahill.beeniusdemo.data.models.PhotoEntity
import si.development.ahill.beeniusdemo.data.repositories.datasources.LocalDataSource
import javax.inject.Inject

/**
 * Created by Andraž Hribar on 8. 11. 2019.
 * andraz.hribar@gmail.com
 */
class PhotoDataSourceLocal @Inject constructor(
    private val photoDao: PhotoDao
) : PhotoDataSource, LocalDataSource<PhotoEntity> {

    private var lastUpdated: Long = 0

    //region PhotoDataSource

    override suspend fun providePhotoById(photoId: Long): PhotoEntity? =
        photoDao.fetchById(photoId)

    override suspend fun providePhotoList(): List<PhotoEntity> =
        photoDao.fetchAll()

    override suspend fun providePhotoListByAlbumId(albumId: Long): List<PhotoEntity> =
        photoDao.fetchByAlbumId(albumId)

    //endregion PhotoDataSource

    //region LocalDataSource<PhotoEntity>

    override suspend fun syncData(list: List<PhotoEntity>) {
        photoDao.insertAll(*list.toTypedArray())
        lastUpdated = System.currentTimeMillis()
    }

    override fun isExpired(): Boolean =
        lastUpdated + LocalDataSource.EXPIRY_INTERVAL < System.currentTimeMillis()

    //endregion LocalDataSource<PhotoEntity>
}