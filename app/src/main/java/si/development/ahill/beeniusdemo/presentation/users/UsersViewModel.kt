package si.development.ahill.beeniusdemo.presentation.users

import androidx.databinding.ObservableBoolean
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import si.development.ahill.beeniusdemo.domain.models.User
import si.development.ahill.beeniusdemo.utils.structures.SelfDestructibleMessage

/**
 * Created by Andraž Hribar on 8. 11. 2019.
 * andraz.hribar@gmail.com
 */
class UsersViewModel : ViewModel(), UsersContract.ViewModel {

    val isLoading = ObservableBoolean()

    private val _error = MutableLiveData<SelfDestructibleMessage<String>>()
    val error: LiveData<SelfDestructibleMessage<String>>
        get() = _error

    private val _users = MutableLiveData<List<User>>()
    val users: LiveData<List<User>>
        get() = _users

    //region UsersContract.ViewModel

    override fun setUsers(userList: List<User>) {
        _users.value = userList
    }

    override fun setIsLoading(isLoading: Boolean) {
        this.isLoading.set(isLoading)
    }

    override fun setError(error: String) {
        _error.value = SelfDestructibleMessage(error)
    }

    //endregion UsersContract.ViewModel
}