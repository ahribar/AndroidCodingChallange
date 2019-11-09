package si.development.ahill.beeniusdemo.presentation.photos

import si.development.ahill.beeniusdemo.dependency.DependencyProvider
import si.development.ahill.beeniusdemo.domain.models.Photo
import si.development.ahill.beeniusdemo.domain.structures.Failure

/**
 * Created by Andraž Hribar on 9. 11. 2019.
 * andraz.hribar@gmail.com
 */
class PhotosPresenter : PhotosContract.Presenter {

    private var viewModel: PhotosContract.ViewModel? = null

    init {
        DependencyProvider.globalComponent.inject(this)
    }

    //region PhotosContract.Presenter

    override fun fetchPhotos(albumId: Long) {
        viewModel?.setIsLoading(true)
    }

    override fun bindViewModel(viewModel: PhotosContract.ViewModel) {
        this.viewModel = viewModel
    }

    override fun unbindViewModel() {
        viewModel = null
    }

    //endregion PhotosContract.Presenter

    private fun handlePhotoList(photoList: List<Photo>) {
        viewModel?.setIsLoading(false)
        viewModel?.setPhotoList(photoList)
    }

    private fun handleFailure(failure: Failure) {
        viewModel?.setIsLoading(false)
    }
}
