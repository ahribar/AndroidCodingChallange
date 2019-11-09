package si.development.ahill.beeniusdemo.presentation.albums

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import androidx.navigation.ui.NavigationUI
import androidx.recyclerview.widget.DividerItemDecoration
import kotlinx.android.synthetic.main.fragment_albums.*
import si.development.ahill.beeniusdemo.R
import si.development.ahill.beeniusdemo.databinding.FragmentAlbumsBinding

class AlbumsFragment : Fragment(), AlbumsContract.View {

    private val arguments: AlbumsFragmentArgs by navArgs()

    private var listAdapter: AlbumsAdapter? = null

    private lateinit var presenter: AlbumsContract.Presenter
    private lateinit var viewModel: AlbumsViewModel

    //region Lifecycle

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        presenter = AlbumsPresenter()
        viewModel = ViewModelProviders.of(this).get(AlbumsViewModel::class.java)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View =
        FragmentAlbumsBinding.inflate(inflater, container, false)
            .apply {
                lifecycleOwner = this@AlbumsFragment
                actions = this@AlbumsFragment
                state = viewModel
                NavigationUI.setupWithNavController(toolbar, findNavController())
                toolbar.title =
                    context?.getString(R.string.fragment_albums_title_, arguments.author)
                recyclerAlbums.setHasFixedSize(true)
                recyclerAlbums.addItemDecoration(
                    DividerItemDecoration(
                        context,
                        DividerItemDecoration.VERTICAL
                    )
                )
            }.root

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        presenter.bindViewModel(viewModel)
        context?.let {
            listAdapter = AlbumsAdapter(it, this@AlbumsFragment)
            recyclerAlbums.adapter = listAdapter
        }
        initDataObservers()
    }

    override fun onResume() {
        super.onResume()
        presenter.fetchAlbums(arguments.userId)
    }

    override fun onDestroy() {
        super.onDestroy()
        presenter.unbindViewModel()
    }

    //endregion Lifecycle

    //region AlbumsContract.View

    override fun refreshData() {
        presenter.fetchAlbums(arguments.userId)
    }

    override fun selectAlbum(albumId: Long) {
        findNavController().navigate(
            AlbumsFragmentDirections.actionAlbumsToPhotos(
                albumId,
                arguments.author
            )
        )
    }

    //endregion AlbumsContract.View

    private fun initDataObservers() {
        viewModel.albums.observe(viewLifecycleOwner, Observer { listAdapter?.data = it })
    }

    companion object {

        fun newInstance() = AlbumsFragment()
    }
}