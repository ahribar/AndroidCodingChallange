package si.development.ahill.beeniusdemo.data.repositories.album.datasources

import si.development.ahill.beeniusdemo.data.database.daos.AlbumDao
import si.development.ahill.beeniusdemo.data.models.AlbumEntity
import si.development.ahill.beeniusdemo.data.repositories.datasources.LocalDataSource
import javax.inject.Inject

/**
 * Created by Andraž Hribar on 8. 11. 2019.
 * andraz.hribar@gmail.com
 */
class AlbumDataSourceLocal @Inject constructor(
    private val albumDao: AlbumDao
) : AlbumDataSource, LocalDataSource<AlbumEntity> {

    private var lastUpdated: Long = 0

    //region AlbumDataSource

    override suspend fun provideAlbumListByUserId(userId: Long): List<AlbumEntity> =
        albumDao.fetchByUserId(userId)

    override suspend fun provideAlbumById(albumId: Long): AlbumEntity? =
        albumDao.fetchByAlbumId(albumId)

    //endregion AlbumDataSource

    //region LocalDataSource<AlbumEntity>

    override suspend fun syncData(list: List<AlbumEntity>) {
        albumDao.insertAll(*list.toTypedArray())
        lastUpdated = System.currentTimeMillis()
    }

    override fun isExpired(): Boolean =
        lastUpdated + LocalDataSource.EXPIRY_INTERVAL < System.currentTimeMillis()

    //endregion LocalDataSource<AlbumEntity>
}