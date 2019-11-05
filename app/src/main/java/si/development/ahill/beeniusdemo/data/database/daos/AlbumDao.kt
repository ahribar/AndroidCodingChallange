package si.development.ahill.beeniusdemo.data.database.daos

import androidx.room.Dao
import androidx.room.Query
import si.development.ahill.beeniusdemo.data.models.AlbumEntity

/**
 * Created by Andraž Hribar on 5. 11. 2019.
 * andraz.hribar@gmail.com
 */
@Dao
abstract class AlbumDao : BaseDao<AlbumEntity> {

    @Query("SELECT * FROM album ORDER BY id")
    abstract suspend fun fetchAll(): List<AlbumEntity>

    @Query("SELECT * FROM album WHERE id = :id")
    abstract suspend fun fetchByAlbumId(id: Long): AlbumEntity?

    @Query("SELECT * FROM album WHERE userId = :userId")
    abstract suspend fun fetchByUserId(userId: Long): List<AlbumEntity>

    @Query("DELETE FROM album")
    abstract override suspend fun deleteAll()
}