package si.development.ahill.beeniusdemo.presentation.users


import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.navigation.fragment.findNavController
import androidx.navigation.ui.NavigationUI
import androidx.recyclerview.widget.DividerItemDecoration
import kotlinx.android.synthetic.main.fragment_users.*
import si.development.ahill.beeniusdemo.R
import si.development.ahill.beeniusdemo.databinding.FragmentUsersBinding

class UsersFragment : Fragment(), UsersContract.View {

    private lateinit var presenter: UsersContract.Presenter
    private lateinit var viewModel: UsersViewModel

    private var listAdapter: UsersAdapter? = null

    //region Lifecycle

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        presenter = UsersPresenter()
        viewModel = ViewModelProviders.of(this).get(UsersViewModel::class.java)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View =
        FragmentUsersBinding.inflate(inflater, container, false)
            .apply {
                lifecycleOwner = this@UsersFragment
                actions = this@UsersFragment
                state = viewModel
                recyclerUsers.setHasFixedSize(true)
                recyclerUsers.addItemDecoration(
                    DividerItemDecoration(
                        context,
                        DividerItemDecoration.VERTICAL
                    )
                )
                toolbar.title = context?.getString(R.string.fragment_users_title)
                NavigationUI.setupWithNavController(toolbar, findNavController())
            }.root

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        presenter.bindViewModel(viewModel)
        context?.let {
            listAdapter = UsersAdapter(it, this@UsersFragment)
            recyclerUsers.adapter = listAdapter
        }
        initDataObservers()
    }

    override fun onResume() {
        super.onResume()
        presenter.fetchUsers()
    }

    override fun onDestroy() {
        super.onDestroy()
        presenter.unbindViewModel()
    }

    //endregion Lifecycle

    //region UsersContract.View

    override fun refreshData() {
        presenter.fetchUsers()
    }

    override fun selectUser(userId: Long) {
        findNavController().navigate(UsersFragmentDirections.actionUsersToAlbums(userId))
    }

    //endregion UsersContract.View

    private fun initDataObservers() {
        viewModel.users.observe(viewLifecycleOwner, Observer { listAdapter?.data = it })
    }

    companion object {

        fun newInstance() = UsersFragment()
    }
}