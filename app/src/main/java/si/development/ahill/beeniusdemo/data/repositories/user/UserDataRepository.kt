package si.development.ahill.beeniusdemo.data.repositories.user

import si.development.ahill.beeniusdemo.domain.models.User
import si.development.ahill.beeniusdemo.domain.repositories.UserRepository

/**
 * Created by Andraž Hribar on 5. 11. 2019.
 * andraz.hribar@gmail.com
 */
class UserDataRepository : UserRepository {

    override fun getUsers(): List<User> {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun getUserById(userId: Long): User? {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }
}