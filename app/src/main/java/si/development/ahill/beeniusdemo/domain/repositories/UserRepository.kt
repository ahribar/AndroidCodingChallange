package si.development.ahill.beeniusdemo.domain.repositories

import si.development.ahill.beeniusdemo.domain.models.User

/**
 * Created by Andraž Hribar on 5. 11. 2019.
 * andraz.hribar@gmail.com
 */
interface UserRepository {

    suspend fun getUsers(): List<User>

    suspend fun getUserById(userId: Long): User?
}