package si.development.ahill.beeniusdemo.domain.interactors.album

import si.development.ahill.beeniusdemo.domain.interactors.BaseInteractor
import si.development.ahill.beeniusdemo.domain.models.Album
import si.development.ahill.beeniusdemo.domain.repositories.AlbumRepository
import si.development.ahill.beeniusdemo.domain.structures.Either
import si.development.ahill.beeniusdemo.domain.structures.Failure
import javax.inject.Inject

/**
 * Created by Andraž Hribar on 9. 11. 2019.
 * andraz.hribar@gmail.com
 */
class GetAlbumInteractor @Inject constructor(
    private val albumRepository: AlbumRepository
) : BaseInteractor<Album?, GetAlbumInteractor.Params>() {

    override suspend fun run(params: Params): Either<Failure, Album?> =
        try {
            Either.Right(albumRepository.getAlbumById(params.albumId))
        } catch (exception: Exception) {
            Either.Left(GetAlbumFailure(exception))
        }

    data class GetAlbumFailure(
        val exception: Exception
    ) : Failure.FeatureFailure(exception)

    class Params(
        val albumId: Long
    ) {

        companion object {

            fun forAlbumId(albumId: Long): Params =
                Params(albumId)
        }
    }
}