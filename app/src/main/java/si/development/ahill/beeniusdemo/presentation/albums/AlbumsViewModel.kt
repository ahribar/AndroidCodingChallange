package si.development.ahill.beeniusdemo.presentation.albums

import androidx.databinding.ObservableBoolean
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import si.development.ahill.beeniusdemo.domain.models.Album

/**
 * Created by Andraž Hribar on 9. 11. 2019.
 * andraz.hribar@gmail.com
 */
class AlbumsViewModel : ViewModel(), AlbumsContract.ViewModel {

    val isLoading = ObservableBoolean()

    val isEmpty = ObservableBoolean()

    private val _albums = MutableLiveData<List<Album>>()
    val albums: LiveData<List<Album>>
        get() = _albums

    //region AlbumsContract.ViewModel

    override fun getAlbumList(): List<Album> =
        _albums.value ?: listOf()

    override fun setAlbumList(albumList: List<Album>) {
        _albums.value = albumList
        isEmpty.set(albumList.isEmpty())
    }

    override fun setIsLoading(isLoading: Boolean) {
        this.isLoading.set(isLoading)
    }

    //endregion AlbumsContract.ViewModel
}