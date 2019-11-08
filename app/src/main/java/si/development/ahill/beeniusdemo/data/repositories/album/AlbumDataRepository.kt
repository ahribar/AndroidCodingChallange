package si.development.ahill.beeniusdemo.data.repositories.album

import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import si.development.ahill.beeniusdemo.data.models.AlbumEntity
import si.development.ahill.beeniusdemo.data.repositories.album.datasources.AlbumDataSource
import si.development.ahill.beeniusdemo.data.repositories.album.datasources.AlbumDataSourceLocal
import si.development.ahill.beeniusdemo.data.repositories.album.datasources.AlbumDataSourceRemote
import si.development.ahill.beeniusdemo.domain.mappers.DomainModelMapper
import si.development.ahill.beeniusdemo.domain.models.Album
import si.development.ahill.beeniusdemo.domain.repositories.AlbumRepository
import javax.inject.Inject

/**
 * Created by Andraž Hribar on 5. 11. 2019.
 * andraz.hribar@gmail.com
 */
class AlbumDataRepository @Inject constructor(
    private val localDataSourceLocal: AlbumDataSourceLocal,
    private val remoteDataSource: AlbumDataSourceRemote,
    private val mapper: DomainModelMapper<AlbumEntity, Album>
) : AlbumRepository {

    override suspend fun getAlbumsByUserId(userId: Long): List<Album> =
        selectDataSource().provideAlbumListByUserId(userId)
            .also { syncLocalIfNecessary(it) }
            .map { mapper.mapAsPresentable(it) }

    override suspend fun getAlbumById(albumId: Long): Album? =
        selectDataSource().provideAlbumById(albumId)?.let { mapper.mapAsPresentable(it) }

    private fun selectDataSource(): AlbumDataSource =
        if (localDataSourceLocal.isExpired()) remoteDataSource else localDataSourceLocal

    private fun syncLocalIfNecessary(users: List<AlbumEntity>) {
        if (localDataSourceLocal.isExpired()) {
            CoroutineScope(Dispatchers.IO).launch {
                localDataSourceLocal.syncData(users)
            }
        }
    }
}