package si.development.ahill.beeniusdemo.data.repositories.user

import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import si.development.ahill.beeniusdemo.data.models.UserEntity
import si.development.ahill.beeniusdemo.data.repositories.user.datasources.UserDataSource
import si.development.ahill.beeniusdemo.data.repositories.user.datasources.UserDataSourceLocal
import si.development.ahill.beeniusdemo.data.repositories.user.datasources.UserDataSourceRemote
import si.development.ahill.beeniusdemo.domain.mappers.DomainModelMapper
import si.development.ahill.beeniusdemo.domain.models.User
import si.development.ahill.beeniusdemo.domain.repositories.UserRepository
import javax.inject.Inject

/**
 * Created by Andraž Hribar on 5. 11. 2019.
 * andraz.hribar@gmail.com
 */
class UserDataRepository @Inject constructor(
    private val localDataSourceLocal: UserDataSourceLocal,
    private val remoteDataSource: UserDataSourceRemote,
    private val mapper: DomainModelMapper<UserEntity, User>
) : UserRepository {

    override suspend fun getUsers(): List<User> =
        selectDataSource().provideUserList()
            .also { syncLocalIfNecessary(it) }
            .map { mapper.mapAsPresentable(it) }

    override suspend fun getUserById(userId: Long): User? =
        selectDataSource().provideUser(userId)?.let { mapper.mapAsPresentable(it) }

    private fun selectDataSource(): UserDataSource =
        if (localDataSourceLocal.isExpired()) remoteDataSource else localDataSourceLocal

    private fun syncLocalIfNecessary(users: List<UserEntity>) {
        if (localDataSourceLocal.isExpired()) {
            CoroutineScope(Dispatchers.IO).launch {
                localDataSourceLocal.syncData(users)
            }
        }
    }
}