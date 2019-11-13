package si.development.ahill.beeniusdemo.presentation.albums

import si.development.ahill.beeniusdemo.dependency.DependencyProvider
import si.development.ahill.beeniusdemo.domain.interactors.album.GetAlbumsInteractor
import si.development.ahill.beeniusdemo.domain.interactors.photo.GetPhotosInteractor
import si.development.ahill.beeniusdemo.domain.models.Album
import si.development.ahill.beeniusdemo.domain.models.Photo
import si.development.ahill.beeniusdemo.utils.structures.Failure
import javax.inject.Inject

/**
 * Created by Andraž Hribar on 9. 11. 2019.
 * andraz.hribar@gmail.com
 */
class AlbumsPresenter : AlbumsContract.Presenter {

    @Inject
    lateinit var getAlbumsInteractor: GetAlbumsInteractor
    @Inject
    lateinit var getPhotosInteractor: GetPhotosInteractor

    private var viewModel: AlbumsContract.ViewModel? = null

    init {
        DependencyProvider.globalComponent.inject(this)
    }

    //region AlbumsContract.Presenter

    override fun fetchAlbums(userId: Long) {
        viewModel?.setIsLoading(true)
        getAlbumsInteractor(GetAlbumsInteractor.Params.forUser(userId)) {
            it.either(::handleFailure, ::handleAlbumList)
        }
    }

    override fun fetchThumbnails() {
        getPhotosInteractor(null) {
            it.either(::handleFailure, ::handlePhotoList)
        }
    }

    override fun bindViewModel(viewModel: AlbumsContract.ViewModel) {
        this.viewModel = viewModel
    }

    override fun unbindViewModel() {
        viewModel = null
    }

    //endregion AlbumsContract.Presenter

    private fun handleAlbumList(albumsList: List<Album>) {
        viewModel?.setAlbumList(albumsList)
        fetchThumbnails()
    }

    private fun handlePhotoList(photoList: List<Photo>) {
        viewModel?.setIsLoading(false)
        viewModel?.let { vm ->
            val thumbnailMap = photoList
                .groupBy { it.albumId ?: 0L }
                .mapValues { it.value.random().thumbnailUrl ?: "" }
            val albumList = vm.getAlbumList()
                .map { Album(it.id, it.title, it.userId, thumbnailMap[it.id]) }
            vm.setAlbumList(albumList)
        }
    }

    private fun handleFailure(failure: Failure) {
        viewModel?.let {
            it.setIsLoading(false)
            when (failure) {
                is GetAlbumsInteractor.GetAlbumsFailure ->
                    it.setError(failure.exception.toString())
                is GetPhotosInteractor.GetPhotosFailure ->
                    it.setError(failure.exception.toString())
            }
        }
    }
}