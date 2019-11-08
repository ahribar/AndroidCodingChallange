package si.development.ahill.beeniusdemo.data.repositories.user.datasources

import si.development.ahill.beeniusdemo.data.mappers.UserMapper
import si.development.ahill.beeniusdemo.data.models.UserEntity
import si.development.ahill.beeniusdemo.data.repositories.datasources.RemoteDataSource
import si.development.ahill.beeniusdemo.data.rest.services.UserRestService
import javax.inject.Inject

/**
 * Created by Andraž Hribar on 8. 11. 2019.
 * andraz.hribar@gmail.com
 */
class UserDataSourceRemote @Inject constructor(
    private val restService: UserRestService,
    private val mapper: UserMapper
) : UserDataSource, RemoteDataSource<UserEntity> {

    //region UserDataSource

    override suspend fun provideUserList(): List<UserEntity> =
        restService.getUsers()
            .map { mapper.mapAsEntity(it) }

    override suspend fun provideUser(userId: Long): UserEntity? =
        restService.getUsers()
            .find { userId == it.id }
            ?.let { mapper.mapAsEntity(it) }

    //endregion UserDataSource
}