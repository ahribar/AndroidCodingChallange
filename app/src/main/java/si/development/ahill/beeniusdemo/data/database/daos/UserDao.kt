package si.development.ahill.beeniusdemo.data.database.daos

import androidx.room.Dao
import androidx.room.Query
import si.development.ahill.beeniusdemo.data.models.UserEntity

/**
 * Created by Andraž Hribar on 5. 11. 2019.
 * andraz.hribar@gmail.com
 */
@Dao
abstract class UserDao : BaseDao<UserEntity> {

    @Query("SELECT * FROM user ORDER BY id")
    abstract suspend fun fetchAll(): List<UserEntity>

    @Query("SELECT * FROM user WHERE id = :id")
    abstract suspend fun fetchById(id: Long): UserEntity?

    @Query("DELETE FROM user")
    abstract override suspend fun deleteAll()
}