package si.development.ahill.beeniusdemo.presentation.albums

import android.widget.ImageView
import androidx.databinding.BindingAdapter
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import si.development.ahill.beeniusdemo.R

/**
 * Created by Andraž Hribar on 13. 11. 2019.
 * andraz.hribar@gmail.com
 */
@BindingAdapter("albumThumbnail")
fun displayThumbnail(imageView: ImageView, thumbnailUrl: String?) {
    Glide.with(imageView.context)
        .load(thumbnailUrl)
        .apply(
            RequestOptions()
                .placeholder(R.drawable.ic_image_placeholder)
        )
        .into(imageView)
}