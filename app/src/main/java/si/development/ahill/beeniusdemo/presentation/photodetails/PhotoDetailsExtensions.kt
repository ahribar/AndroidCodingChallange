package si.development.ahill.beeniusdemo.presentation.photodetails

import android.widget.ImageView
import androidx.databinding.BindingAdapter
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import si.development.ahill.beeniusdemo.R

/**
 * Created by Andraž Hribar on 13. 11. 2019.
 * andraz.hribar@gmail.com
 */
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