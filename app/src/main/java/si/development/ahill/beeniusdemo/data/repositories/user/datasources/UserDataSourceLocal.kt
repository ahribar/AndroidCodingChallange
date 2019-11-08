package si.development.ahill.beeniusdemo.data.repositories.user.datasources

import si.development.ahill.beeniusdemo.data.database.daos.UserDao
import si.development.ahill.beeniusdemo.data.models.UserEntity
import si.development.ahill.beeniusdemo.data.repositories.datasources.LocalDataSource
import si.development.ahill.beeniusdemo.data.repositories.datasources.LocalDataSource.Companion.EXPIRY_INTERVAL
import javax.inject.Inject

/**
 * Created by Andraž Hribar on 8. 11. 2019.
 * andraz.hribar@gmail.com
 */
class UserDataSourceLocal @Inject constructor(
    private val userDao: UserDao
) : UserDataSource, LocalDataSource<UserEntity> {

    private var lastUpdated: Long = 0

    //region UserDataSource

    override suspend fun provideUserList(): List<UserEntity> =
        userDao.fetchAll()

    override suspend fun provideUser(userId: Long): UserEntity? =
        userDao.fetchById(userId)


    //endregion UserDataSource

    //region LocalDataSource<UserEntity>

    override suspend fun syncData(list: List<UserEntity>) {
        userDao.insertAll(*list.toTypedArray())
        lastUpdated = System.currentTimeMillis()
    }

    override fun isExpired(): Boolean =
        lastUpdated + EXPIRY_INTERVAL < System.currentTimeMillis()

    //endregion LocalDataSource<UserEntity>
}