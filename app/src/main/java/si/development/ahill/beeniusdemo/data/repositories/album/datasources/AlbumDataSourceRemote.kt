package si.development.ahill.beeniusdemo.data.repositories.album.datasources

import si.development.ahill.beeniusdemo.data.mappers.AlbumMapper
import si.development.ahill.beeniusdemo.data.models.AlbumEntity
import si.development.ahill.beeniusdemo.data.repositories.datasources.RemoteDataSource
import si.development.ahill.beeniusdemo.data.rest.services.AlbumRestService
import javax.inject.Inject

/**
 * Created by Andraž Hribar on 8. 11. 2019.
 * andraz.hribar@gmail.com
 */
class AlbumDataSourceRemote @Inject constructor(
    private val restService: AlbumRestService,
    private val mapper: AlbumMapper
) : AlbumDataSource, RemoteDataSource<AlbumEntity> {

    //region AlbumDataSource

    override suspend fun provideAlbumListByUserId(userId: Long): List<AlbumEntity> =
        restService.getAlbums()
            .filter { userId == it.userId }
            .map { mapper.mapAsEntity(it) }

    override suspend fun provideAlbumById(albumId: Long): AlbumEntity? =
        restService.getAlbums()
            .find { albumId == it.id }
            ?.let { mapper.mapAsEntity(it) }

    //endregion AlbumDataSource
}