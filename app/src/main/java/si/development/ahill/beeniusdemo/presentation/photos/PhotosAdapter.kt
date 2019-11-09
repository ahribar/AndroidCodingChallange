package si.development.ahill.beeniusdemo.presentation.photos

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.ImageView
import androidx.databinding.BindingAdapter
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import si.development.ahill.beeniusdemo.R
import si.development.ahill.beeniusdemo.databinding.ItemPhotoBinding
import si.development.ahill.beeniusdemo.domain.models.Photo

/**
 * Created by Andraž Hribar on 9. 11. 2019.
 * andraz.hribar@gmail.com
 */
class PhotosAdapter(
    val context: Context,
    val view: PhotosContract.View
) : RecyclerView.Adapter<PhotosAdapter.PhotosViewHolder>() {

    var data: List<Photo> = mutableListOf()
        set(value) {
            val diffResult: DiffUtil.DiffResult =
                DiffUtil.calculateDiff(PhotoItemDiffCallback(field, value))
            field = value
            diffResult.dispatchUpdatesTo(this)
        }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PhotosViewHolder {
        val itemPhotoBinding: ItemPhotoBinding = DataBindingUtil.inflate(
            LayoutInflater.from(context),
            R.layout.item_photo,
            parent,
            false
        )
        return PhotosViewHolder(
            itemPhotoBinding
        )
    }

    override fun getItemCount(): Int =
        data.size

    override fun onBindViewHolder(holder: PhotosViewHolder, position: Int) {
        holder.itemPhotoBinding.photo = data[position]
        holder.itemPhotoBinding.actions = view
    }

    class PhotoItemDiffCallback(
        private var oldList: List<Photo>,
        private var newList: List<Photo>
    ) : DiffUtil.Callback() {

        override fun areItemsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean =
            oldList[oldItemPosition].id == newList[newItemPosition].id

        override fun getOldListSize(): Int =
            oldList.size

        override fun getNewListSize(): Int =
            newList.size

        override fun areContentsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean =
            oldList[oldItemPosition] == newList[newItemPosition]
    }

    class PhotosViewHolder(
        val itemPhotoBinding: ItemPhotoBinding
    ) : RecyclerView.ViewHolder(itemPhotoBinding.root)

    companion object {

        @JvmStatic
        @BindingAdapter("photoThumbnail")
        fun displayThumbnail(imageView: ImageView, thumbnailUrl: String?) {
            Glide.with(imageView.context)
                .load(thumbnailUrl)
                .thumbnail(
                    Glide.with(imageView.context)
                        .load(R.drawable.ic_image_placeholder)
                        .apply(
                            RequestOptions()
                                .dontAnimate()
                                .fitCenter()
                        )
                )
                .apply(
                    RequestOptions()
                        .dontAnimate()
                        .fitCenter()
                )
                .into(imageView)
        }
    }
}