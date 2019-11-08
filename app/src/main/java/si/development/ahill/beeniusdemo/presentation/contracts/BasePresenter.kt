package si.development.ahill.beeniusdemo.presentation.contracts

/**
 * Created by Andraž Hribar on 8. 11. 2019.
 * andraz.hribar@gmail.com
 */
interface BasePresenter<T : BaseViewModel> {

    fun bindViewModel(viewModel: T)

    fun unbindViewModel()
}