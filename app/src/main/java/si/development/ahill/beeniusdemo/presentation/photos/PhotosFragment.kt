package si.development.ahill.beeniusdemo.presentation.photos

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
import kotlinx.android.synthetic.main.fragment_photos.*
import si.development.ahill.beeniusdemo.R
import si.development.ahill.beeniusdemo.databinding.FragmentPhotosBinding
import si.development.ahill.beeniusdemo.presentation.photodetails.PhotoDetailsFragment
import si.development.ahill.beeniusdemo.utils.AdaptableGridLayoutManager

class PhotosFragment : Fragment(), PhotosContract.View {

    private val arguments: PhotosFragmentArgs by navArgs()

    private var gridAdapter: PhotosAdapter? = null

    private lateinit var presenter: PhotosContract.Presenter
    private lateinit var viewModel: PhotosViewModel

    //region Lifecycle

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        presenter = PhotosPresenter()
        viewModel = ViewModelProviders.of(this).get(PhotosViewModel::class.java)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        presenter.bindViewModel(viewModel)
        context?.let {
            gridAdapter = PhotosAdapter(it, this@PhotosFragment)
            recyclerPhotos.layoutManager = AdaptableGridLayoutManager(it)
            recyclerPhotos.adapter = gridAdapter
        }
        initDataObservers()
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View =
        FragmentPhotosBinding.inflate(inflater, container, false)
            .apply {
                lifecycleOwner = this@PhotosFragment
                actions = this@PhotosFragment
                state = viewModel
                toolbar.title =
                    context?.getString(R.string.fragment_photos_title_, arguments.author)
                NavigationUI.setupWithNavController(toolbar, findNavController())
                recyclerPhotos.setHasFixedSize(true)
            }.root

    override fun onResume() {
        super.onResume()
        refreshData()
    }

    override fun onDestroy() {
        super.onDestroy()
        presenter.unbindViewModel()
    }

    //endregion Lifecycle

    //region PhotosContract.View

    override fun refreshData() {
        presenter.fetchPhotos(arguments.albumId)
    }

    override fun selectPhoto(photoId: Long, photoTitle: String) {
        findNavController().navigate(
            PhotosFragmentDirections.actionPhotosToPhotodetails(photoId, photoTitle)
        )
    }

    //endregion PhotosContract.View

    private fun initDataObservers() {
        viewModel.photos.observe(viewLifecycleOwner, Observer { gridAdapter?.data = it })
    }

    companion object {

        fun newInstance() = PhotoDetailsFragment()
    }
}