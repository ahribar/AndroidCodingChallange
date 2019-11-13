package si.development.ahill.beeniusdemo.presentation.photodetails

import si.development.ahill.beeniusdemo.domain.models.Album
import si.development.ahill.beeniusdemo.domain.models.Photo
import si.development.ahill.beeniusdemo.domain.models.User
import si.development.ahill.beeniusdemo.presentation.contracts.BasePresenter
import si.development.ahill.beeniusdemo.presentation.contracts.BaseView
import si.development.ahill.beeniusdemo.presentation.contracts.BaseViewModel

/**
 * Created by Andraž Hribar on 9. 11. 2019.
 * andraz.hribar@gmail.com
 */
interface PhotoDetailsContract {

    interface View : BaseView<ViewModel, Presenter> {

        fun toggleDetails()
    }

    interface Presenter : BasePresenter<ViewModel> {

        fun fetchPhoto(photoId: Long)

        fun fetchAlbum(albumId: Long)

        fun fetchUser(userId: Long)
    }

    interface ViewModel : BaseViewModel {

        fun setPhoto(photo: Photo)

        fun setAlbum(album: Album)

        fun setUser(user: User)

        fun setDetailsVisibility(isVisible: Boolean)
    }
}