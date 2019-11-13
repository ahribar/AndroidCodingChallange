package si.development.ahill.beeniusdemo.presentation.albums

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import si.development.ahill.beeniusdemo.R
import si.development.ahill.beeniusdemo.databinding.ItemAlbumBinding
import si.development.ahill.beeniusdemo.domain.models.Album

/**
 * Created by Andraž Hribar on 9. 11. 2019.
 * andraz.hribar@gmail.com
 */
class AlbumsAdapter(
    val context: Context,
    val view: AlbumsContract.View
) : RecyclerView.Adapter<AlbumsAdapter.AlbumsViewHolder>() {

    var data: List<Album> = mutableListOf()
        set(value) {
            val diffResult: DiffUtil.DiffResult =
                DiffUtil.calculateDiff(AlbumItemDiffCallback(field, value))
            field = value
            diffResult.dispatchUpdatesTo(this)
        }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): AlbumsViewHolder {
        val itemAlbumBinding: ItemAlbumBinding = DataBindingUtil.inflate(
            LayoutInflater.from(context),
            R.layout.item_album,
            parent,
            false
        )
        return AlbumsViewHolder(itemAlbumBinding)
    }

    override fun getItemCount(): Int =
        data.size

    override fun onBindViewHolder(holder: AlbumsViewHolder, position: Int) {
        holder.itemAlbumBinding.album = data[position]
        holder.itemAlbumBinding.actions = view
    }

    class AlbumItemDiffCallback(
        private var oldList: List<Album>,
        private var newList: List<Album>
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

    class AlbumsViewHolder(
        val itemAlbumBinding: ItemAlbumBinding
    ) : RecyclerView.ViewHolder(itemAlbumBinding.root)
}