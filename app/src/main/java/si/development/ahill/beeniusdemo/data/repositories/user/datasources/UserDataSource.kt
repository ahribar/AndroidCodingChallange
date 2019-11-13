package si.development.ahill.beeniusdemo.data.repositories.user.datasources

import si.development.ahill.beeniusdemo.data.models.UserEntity

/**
 * Created by Andraž Hribar on 8. 11. 2019.
 * andraz.hribar@gmail.com
 */
interface UserDataSource {

    suspend fun provideUserList(): List<UserEntity>

    suspend fun provideUser(userId: Long): UserEntity?
}