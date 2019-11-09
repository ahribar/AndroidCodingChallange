package si.development.ahill.beeniusdemo.domain.interactors.photo

import si.development.ahill.beeniusdemo.domain.interactors.BaseInteractor
import si.development.ahill.beeniusdemo.domain.models.Photo
import si.development.ahill.beeniusdemo.domain.repositories.PhotoRepository
import si.development.ahill.beeniusdemo.domain.structures.Either
import si.development.ahill.beeniusdemo.domain.structures.Failure
import javax.inject.Inject

/**
 * Created by Andraž Hribar on 9. 11. 2019.
 * andraz.hribar@gmail.com
 */
class GetPhotoInteractor @Inject constructor(
    private val photoRepository: PhotoRepository
) : BaseInteractor<Photo?, GetPhotoInteractor.Params>() {

    override suspend fun run(params: Params): Either<Failure, Photo?> =
        try {
            Either.Right(photoRepository.getPhotoById(params.photoId))
        } catch (exception: Exception) {
            Either.Left(GetPhotosFailure(exception))
        }

    data class GetPhotosFailure(
        val exception: Exception
    ) : Failure.FeatureFailure(exception)

    class Params(
        val photoId: Long
    ) {

        companion object {

            fun forPhotoId(photoId: Long): Params =
                Params(photoId)
        }
    }
}