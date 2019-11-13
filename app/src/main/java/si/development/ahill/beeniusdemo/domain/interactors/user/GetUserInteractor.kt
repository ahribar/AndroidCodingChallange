package si.development.ahill.beeniusdemo.domain.interactors.user

import si.development.ahill.beeniusdemo.domain.interactors.BaseInteractor
import si.development.ahill.beeniusdemo.domain.models.User
import si.development.ahill.beeniusdemo.domain.repositories.UserRepository
import si.development.ahill.beeniusdemo.utils.structures.Either
import si.development.ahill.beeniusdemo.utils.structures.Failure
import javax.inject.Inject

/**
 * Created by Andraž Hribar on 8. 11. 2019.
 * andraz.hribar@gmail.com
 */
class GetUserInteractor @Inject constructor(
    private val userRepository: UserRepository
) : BaseInteractor<User, GetUserInteractor.Params>() {

    override suspend fun run(params: Params): Either<Failure, User> =
        try {
            userRepository.getUserById(params.userId)?.let {
                Either.Right(it)
            } ?: Either.Left(GetUsersFailure(NullPointerException()))
        } catch (exception: Exception) {
            Either.Left(GetUsersFailure(exception))
        }

    data class GetUsersFailure(
        override val exception: Exception
    ) : Failure.FeatureFailure(exception)

    class Params(
        val userId: Long
    ) {

        companion object {

            fun forUserId(userId: Long): Params =
                Params(userId)
        }
    }
}