package si.development.ahill.beeniusdemo.domain.interactors.user

import si.development.ahill.beeniusdemo.domain.interactors.BaseInteractor
import si.development.ahill.beeniusdemo.domain.models.User
import si.development.ahill.beeniusdemo.domain.repositories.UserRepository
import si.development.ahill.beeniusdemo.domain.structures.Either
import si.development.ahill.beeniusdemo.domain.structures.Failure
import javax.inject.Inject

/**
 * Created by Andraž Hribar on 8. 11. 2019.
 * andraz.hribar@gmail.com
 */
class GetUsersInteractor @Inject constructor(
    private val userRepository: UserRepository
) : BaseInteractor<List<User>, Any?>() {

    override suspend fun run(params: Any?): Either<Failure, List<User>> =
        try {
            Either.Right(userRepository.getUsers())
        } catch (exception: Exception) {
            Either.Left(GetUsersFailure(exception))
        }

    data class GetUsersFailure(
        val exception: Exception
    ) : Failure.FeatureFailure(exception)
}