package si.development.ahill.beeniusdemo.presentation.users

import si.development.ahill.beeniusdemo.domain.models.User
import si.development.ahill.beeniusdemo.presentation.contracts.BasePresenter
import si.development.ahill.beeniusdemo.presentation.contracts.BaseView
import si.development.ahill.beeniusdemo.presentation.contracts.BaseViewModel

/**
 * Created by Andraž Hribar on 8. 11. 2019.
 * andraz.hribar@gmail.com
 */
interface UsersContract {

    interface View : BaseView<ViewModel, Presenter> {
        fun refreshData()

        fun selectUser(userId: Long)
    }

    interface Presenter : BasePresenter<ViewModel> {
        fun fetchUsers()
    }

    interface ViewModel : BaseViewModel {
        fun setUsers(userList: List<User>)

        fun setIsLoading(isLoading: Boolean)
    }
}