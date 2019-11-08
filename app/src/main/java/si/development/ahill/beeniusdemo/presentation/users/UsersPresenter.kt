package si.development.ahill.beeniusdemo.presentation.users

/**
 * Created by Andraž Hribar on 8. 11. 2019.
 * andraz.hribar@gmail.com
 */
class UsersPresenter : UsersContract.Presenter {

    private var viewModel: UsersContract.ViewModel? = null

    //region UsersContract.Presenter

    override fun fetchUsers() {
        viewModel?.setIsLoading(true)
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
}