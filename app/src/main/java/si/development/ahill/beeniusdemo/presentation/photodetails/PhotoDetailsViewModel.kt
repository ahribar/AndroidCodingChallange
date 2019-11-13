package si.development.ahill.beeniusdemo.presentation.photodetails

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import si.development.ahill.beeniusdemo.domain.models.Album
import si.development.ahill.beeniusdemo.domain.models.Photo
import si.development.ahill.beeniusdemo.domain.models.User
import si.development.ahill.beeniusdemo.utils.structures.SelfDestructibleMessage

/**
 * Created by Andraž Hribar on 10. 11. 2019.
 * andraz.hribar@gmail.com
 */
class PhotoDetailsViewModel : ViewModel(), PhotoDetailsContract.ViewModel {

    private val _error = MutableLiveData<SelfDestructibleMessage<String>>()
    val error: LiveData<SelfDestructibleMessage<String>>
        get() = _error

    private val _photo = MutableLiveData<Photo?>()
    val photo: LiveData<Photo?>
        get() = _photo

    private val _album = MutableLiveData<Album?>()
    val album: LiveData<Album?>
        get() = _album

    private val _user = MutableLiveData<User?>()
    val user: LiveData<User?>
        get() = _user

    private val _areDetailsVisible = MutableLiveData<Boolean>()
    val areDetailsVisible: LiveData<Boolean>
        get() = _areDetailsVisible

    //region PhotoDetailsContract.ViewModel

    override fun setPhoto(photo: Photo) {
        _photo.value = photo
    }

    override fun setAlbum(album: Album) {
        _album.value = album
    }

    override fun setUser(user: User) {
        _user.value = user
    }

    override fun setDetailsVisibility(isVisible: Boolean) {
        _areDetailsVisible.value = isVisible
    }

    override fun setError(error: String) {
        _error.value = SelfDestructibleMessage(error)
    }

    //endregion PhotoDetailsContract.ViewModel
}