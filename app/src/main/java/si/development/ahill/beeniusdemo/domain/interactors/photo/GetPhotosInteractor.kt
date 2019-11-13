package si.development.ahill.beeniusdemo.domain.interactors.photo

import si.development.ahill.beeniusdemo.domain.interactors.BaseInteractor
import si.development.ahill.beeniusdemo.domain.models.Photo
import si.development.ahill.beeniusdemo.domain.repositories.PhotoRepository
import si.development.ahill.beeniusdemo.utils.structures.Either
import si.development.ahill.beeniusdemo.utils.structures.Failure
import javax.inject.Inject

/**
 * Created by Andraž Hribar on 9. 11. 2019.
 * andraz.hribar@gmail.com
 */
class GetPhotosInteractor @Inject constructor(
    private val photoRepository: PhotoRepository
) : BaseInteractor<List<Photo>, Any?>() {

    override suspend fun run(params: Any?): Either<Failure, List<Photo>> =
        try {
            Either.Right(photoRepository.getPhotos())
        } catch (exception: Exception) {
            Either.Left(GetPhotosFailure(exception))
        }

    data class GetPhotosFailure(
        override val exception: Exception
    ) : Failure.FeatureFailure(exception)
}