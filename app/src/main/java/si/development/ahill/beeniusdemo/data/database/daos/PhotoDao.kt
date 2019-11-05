package si.development.ahill.beeniusdemo.data.database.daos

import androidx.room.Dao
import androidx.room.Query
import si.development.ahill.beeniusdemo.data.models.PhotoEntity

/**
 * Created by Andraž Hribar on 5. 11. 2019.
 * andraz.hribar@gmail.com
 */
@Dao
abstract class PhotoDao : BaseDao<PhotoEntity> {

    @Query("SELECT * FROM photo ORDER BY id")
    abstract suspend fun fetchAll(): List<PhotoEntity>

    @Query("SELECT * FROM photo WHERE id = :id")
    abstract suspend fun fetchById(id: Long): PhotoEntity?

    @Query("SELECT * FROM photo WHERE albumId = :albumId")
    abstract suspend fun fetchByAlbumId(albumId: Long): List<PhotoEntity>

    @Query("DELETE FROM photo")
    abstract override suspend fun deleteAll()
}