package si.development.ahill.beeniusdemo.presentation.photos

import si.development.ahill.beeniusdemo.domain.models.Photo
import si.development.ahill.beeniusdemo.presentation.contracts.BasePresenter
import si.development.ahill.beeniusdemo.presentation.contracts.BaseView
import si.development.ahill.beeniusdemo.presentation.contracts.BaseViewModel

/**
 * Created by Andraž Hribar on 9. 11. 2019.
 * andraz.hribar@gmail.com
 */
interface PhotosContract {

    interface View : BaseView<ViewModel, Presenter> {

        fun refreshData()

        fun selectPhoto(photoId: Long, photoTitle: String)
    }

    interface Presenter : BasePresenter<ViewModel> {

        fun fetchPhotos(albumId: Long)
    }

    interface ViewModel : BaseViewModel {

        fun setPhotoList(photoList: List<Photo>)

        fun setIsLoading(isLoading: Boolean)
    }
}