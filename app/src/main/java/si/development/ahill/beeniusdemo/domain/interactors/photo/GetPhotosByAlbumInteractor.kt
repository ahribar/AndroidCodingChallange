package si.development.ahill.beeniusdemo.domain.interactors.photo

import si.development.ahill.beeniusdemo.domain.interactors.BaseInteractor
import si.development.ahill.beeniusdemo.domain.models.Photo
import si.development.ahill.beeniusdemo.domain.repositories.PhotoRepository
import si.development.ahill.beeniusdemo.domain.structures.Either
import si.development.ahill.beeniusdemo.domain.structures.Failure
import javax.inject.Inject

/**
 * Created by Andraž Hribar on 10. 11. 2019.
 * andraz.hribar@gmail.com
 */
class GetPhotosByAlbumInteractor @Inject constructor(
    private val photoRepository: PhotoRepository
) : BaseInteractor<List<Photo>, GetPhotosByAlbumInteractor.Params>() {

    override suspend fun run(params: Params): Either<Failure, List<Photo>> =
        try {
            Either.Right(photoRepository.getPhotosByAlbumId(params.albumId))
        } catch (exception: Exception) {
            Either.Left(GetPhotosFailure(exception))
        }

    data class GetPhotosFailure(
        val exception: Exception
    ) : Failure.FeatureFailure(exception)

    class Params(
        val albumId: Long
    ) {

        companion object {

            fun forAlbum(albumId: Long): Params =
                Params(albumId)
        }
    }
}