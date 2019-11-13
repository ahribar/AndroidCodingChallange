package si.development.ahill.beeniusdemo.domain.interactors.album

import si.development.ahill.beeniusdemo.domain.interactors.BaseInteractor
import si.development.ahill.beeniusdemo.domain.models.Album
import si.development.ahill.beeniusdemo.domain.repositories.AlbumRepository
import si.development.ahill.beeniusdemo.utils.structures.Either
import si.development.ahill.beeniusdemo.utils.structures.Failure
import javax.inject.Inject

/**
 * Created by Andraž Hribar on 9. 11. 2019.
 * andraz.hribar@gmail.com
 */
class GetAlbumsInteractor @Inject constructor(
    private val albumRepository: AlbumRepository
) : BaseInteractor<List<Album>, GetAlbumsInteractor.Params>() {

    override suspend fun run(params: Params): Either<Failure, List<Album>> =
        try {
            Either.Right(albumRepository.getAlbumsByUserId(params.userId))
        } catch (exception: Exception) {
            Either.Left(GetAlbumsFailure(exception))
        }

    data class GetAlbumsFailure(
        override val exception: Exception
    ) : Failure.FeatureFailure(exception)

    class Params(
        val userId: Long
    ) {

        companion object {

            fun forUser(userId: Long): Params =
                Params(userId)
        }
    }
}