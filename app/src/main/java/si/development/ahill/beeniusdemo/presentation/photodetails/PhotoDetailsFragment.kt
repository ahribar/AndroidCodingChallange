package si.development.ahill.beeniusdemo.presentation.photodetails

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.databinding.BindingAdapter
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import androidx.navigation.ui.NavigationUI
import androidx.transition.TransitionManager
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.google.android.material.bottomsheet.BottomSheetBehavior
import kotlinx.android.synthetic.main.fragment_photo_details.*
import si.development.ahill.beeniusdemo.R
import si.development.ahill.beeniusdemo.databinding.FragmentPhotoDetailsBinding

class PhotoDetailsFragment : Fragment(), PhotoDetailsContract.View {

    private val arguments: PhotoDetailsFragmentArgs by navArgs()

    private lateinit var presenter: PhotoDetailsContract.Presenter
    private lateinit var viewModel: PhotoDetailsViewModel

    //region Lifecycle

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        presenter = PhotoDetailsPresenter()
        viewModel = ViewModelProviders.of(this).get(PhotoDetailsViewModel::class.java)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View =
        FragmentPhotoDetailsBinding.inflate(inflater, container, false)
            .apply {
                lifecycleOwner = this@PhotoDetailsFragment
                actions = this@PhotoDetailsFragment
                state = viewModel
                with(bottomSheet) {
                    photo = viewModel.photo.value
                    album = viewModel.album.value
                    user = viewModel.user.value
                }
                toolbar.title = context?.getString(R.string.fragment_photo_title)
                NavigationUI.setupWithNavController(toolbar, findNavController())
            }.root

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        presenter.bindViewModel(viewModel)
        initDataObservers()
        viewModel.setDetailsVisibility(true)
    }

    override fun onResume() {
        super.onResume()
        presenter.fetchPhoto(arguments.photoId)
    }

    override fun onDestroy() {
        super.onDestroy()
        presenter.unbindViewModel()
    }

    //endregion Lifecycle

    //region PhotoDetailsContract.View

    override fun toggleDetails() {
        viewModel.setDetailsVisibility(viewModel.areDetailsVisible.value?.not() ?: false)
    }

    //endregion PhotoDetailsContract.View

    private fun initDataObservers() {
        viewModel.areDetailsVisible.observe(
            viewLifecycleOwner,
            Observer { setHudVisibility(it) }
        )
    }

    private fun setHudVisibility(isVisible: Boolean) {
        val bottomSheetBehavior = BottomSheetBehavior.from(bottomSheet)
        TransitionManager.beginDelayedTransition(rootLayout)
        if (isVisible) {
            toolbar.visibility = View.VISIBLE
            bottomSheetBehavior.state = BottomSheetBehavior.STATE_EXPANDED
        } else {
            toolbar.visibility = View.GONE
            bottomSheetBehavior.state = BottomSheetBehavior.STATE_COLLAPSED
        }
    }

    companion object {

        fun newInstance() = PhotoDetailsFragment()

        @JvmStatic
        @BindingAdapter("photoImage")
        fun displayImage(imageView: ImageView, thumbnailUrl: String?) {
            Glide.with(imageView.context)
                .load(thumbnailUrl)
                .thumbnail(
                    Glide.with(imageView.context)
                        .load(R.drawable.ic_image_placeholder)
                        .apply(RequestOptions().fitCenter())
                )
                .apply(RequestOptions().fitCenter())
                .into(imageView)
        }
    }
}