package si.development.ahill.beeniusdemo.data.repositories.album.datasources

import si.development.ahill.beeniusdemo.data.models.AlbumEntity

/**
 * Created by Andraž Hribar on 8. 11. 2019.
 * andraz.hribar@gmail.com
 */
interface AlbumDataSource {

    suspend fun provideAlbumListByUserId(userId: Long): List<AlbumEntity>

    suspend fun provideAlbumById(albumId: Long): AlbumEntity?
}