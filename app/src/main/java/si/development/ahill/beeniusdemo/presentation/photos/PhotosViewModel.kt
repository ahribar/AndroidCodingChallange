package si.development.ahill.beeniusdemo.presentation.photos

import androidx.databinding.ObservableBoolean
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import si.development.ahill.beeniusdemo.domain.models.Photo

/**
 * Created by Andraž Hribar on 9. 11. 2019.
 * andraz.hribar@gmail.com
 */
class PhotosViewModel : ViewModel(), PhotosContract.ViewModel {

    val isLoading = ObservableBoolean()

    val isEmpty = ObservableBoolean()

    private val _photos = MutableLiveData<List<Photo>>()
    val photos: LiveData<List<Photo>>
        get() = _photos

    //region PhotosContract.ViewModel

    override fun setPhotoList(photoList: List<Photo>) {
        _photos.value = photoList
        isEmpty.set(photoList.isEmpty())
    }

    override fun setIsLoading(isLoading: Boolean) {
        this.isLoading.set(isLoading)
    }

    //endregion PhotosContract.ViewModel
}