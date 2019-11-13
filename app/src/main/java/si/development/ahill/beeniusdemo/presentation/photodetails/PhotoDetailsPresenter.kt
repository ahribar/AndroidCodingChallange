package si.development.ahill.beeniusdemo.presentation.photodetails

import si.development.ahill.beeniusdemo.dependency.DependencyProvider
import si.development.ahill.beeniusdemo.domain.interactors.album.GetAlbumInteractor
import si.development.ahill.beeniusdemo.domain.interactors.photo.GetPhotoInteractor
import si.development.ahill.beeniusdemo.domain.interactors.user.GetUserInteractor
import si.development.ahill.beeniusdemo.domain.models.Album
import si.development.ahill.beeniusdemo.domain.models.Photo
import si.development.ahill.beeniusdemo.domain.models.User
import si.development.ahill.beeniusdemo.utils.structures.Failure
import javax.inject.Inject

/**
 * Created by Andraž Hribar on 10. 11. 2019.
 * andraz.hribar@gmail.com
 */
class PhotoDetailsPresenter : PhotoDetailsContract.Presenter {

    @Inject
    lateinit var getPhotoInteractor: GetPhotoInteractor
    @Inject
    lateinit var getAlbumInteractor: GetAlbumInteractor
    @Inject
    lateinit var getUserInteractor: GetUserInteractor

    private var viewModel: PhotoDetailsContract.ViewModel? = null

    init {
        DependencyProvider.globalComponent.inject(this)
    }

    //region PhotoDetailsContract.Presenter

    override fun fetchPhoto(photoId: Long) {
        getPhotoInteractor(GetPhotoInteractor.Params.forPhotoId(photoId)) {
            it.either(::handleFailure, ::handlePhoto)
        }
    }

    override fun fetchAlbum(albumId: Long) {
        getAlbumInteractor(GetAlbumInteractor.Params.forAlbumId(albumId)) {
            it.either(::handleFailure, ::handleAlbum)
        }
    }

    override fun fetchUser(userId: Long) {
        getUserInteractor(GetUserInteractor.Params.forUserId(userId)) {
            it.either(::handleFailure, ::handleUser)
        }
    }

    override fun bindViewModel(viewModel: PhotoDetailsContract.ViewModel) {
        this.viewModel = viewModel
    }

    override fun unbindViewModel() {
        viewModel = null
    }

    //endregion PhotoDetailsContract.Presenter

    private fun handlePhoto(photo: Photo?) {
        photo?.let {
            viewModel?.setPhoto(it)
            it.albumId?.let { albumId -> fetchAlbum(albumId) }
        }
    }

    private fun handleAlbum(album: Album?) {
        album?.let {
            viewModel?.setAlbum(it)
            it.userId?.let { userId -> fetchUser(userId) }
        }
    }

    private fun handleUser(user: User?) {
        user?.let { viewModel?.setUser(it) }
    }

    private fun handleFailure(failure: Failure) {
        when (failure) {
            is GetPhotoInteractor.GetPhotosFailure ->
                viewModel?.setError(failure.exception.toString())
            is GetAlbumInteractor.GetAlbumFailure ->
                viewModel?.setError(failure.exception.toString())
            is GetUserInteractor.GetUsersFailure ->
                viewModel?.setError(failure.exception.toString())
        }
    }
}