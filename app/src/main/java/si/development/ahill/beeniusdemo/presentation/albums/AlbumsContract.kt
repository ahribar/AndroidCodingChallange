package si.development.ahill.beeniusdemo.presentation.albums

import si.development.ahill.beeniusdemo.domain.models.Album
import si.development.ahill.beeniusdemo.presentation.contracts.BasePresenter
import si.development.ahill.beeniusdemo.presentation.contracts.BaseView
import si.development.ahill.beeniusdemo.presentation.contracts.BaseViewModel

/**
 * Created by Andraž Hribar on 9. 11. 2019.
 * andraz.hribar@gmail.com
 */
interface AlbumsContract {

    interface View : BaseView<ViewModel, Presenter> {

        fun refreshData()

        fun selectAlbum(albumId: Long)
    }

    interface Presenter : BasePresenter<ViewModel> {

        fun fetchAlbums(userId: Long)

        fun fetchThumbnails()
    }

    interface ViewModel : BaseViewModel {

        fun getAlbumList(): List<Album>

        fun setAlbumList(albumList: List<Album>)

        fun setIsLoading(isLoading: Boolean)
    }
}