package si.development.ahill.beeniusdemo.presentation.users

import si.development.ahill.beeniusdemo.dependency.DependencyProvider
import si.development.ahill.beeniusdemo.domain.interactors.user.GetUsersInteractor
import si.development.ahill.beeniusdemo.domain.models.User
import si.development.ahill.beeniusdemo.utils.structures.Failure
import javax.inject.Inject

/**
 * Created by Andraž Hribar on 8. 11. 2019.
 * andraz.hribar@gmail.com
 */
class UsersPresenter : UsersContract.Presenter {

    @Inject
    lateinit var getUsersInteractor: GetUsersInteractor

    private var viewModel: UsersContract.ViewModel? = null

    init {
        DependencyProvider.globalComponent.inject(this)
    }

    //region UsersContract.Presenter

    override fun fetchUsers() {
        viewModel?.setIsLoading(true)
        getUsersInteractor(null) {
            it.either(::handleFailure, ::handleUserList)
        }
    }

    //endregion UsersContract.Presenter

    //region UsersContract.Presenter

    override fun bindViewModel(viewModel: UsersContract.ViewModel) {
        this.viewModel = viewModel
    }

    override fun unbindViewModel() {
        viewModel = null
    }

    //endregion UsersContract.Presenter

    private fun handleUserList(userList: List<User>) {
        viewModel?.setIsLoading(false)
        viewModel?.setUsers(userList)
    }

    private fun handleFailure(failure: Failure) {
        viewModel?.let {
            it.setIsLoading(false)
            if (failure is GetUsersInteractor.GetUsersFailure) {
                it.setError(failure.exception.toString())
            }
        }
    }
}