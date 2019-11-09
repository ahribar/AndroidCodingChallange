package si.development.ahill.beeniusdemo.presentation.users

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import si.development.ahill.beeniusdemo.R
import si.development.ahill.beeniusdemo.databinding.ItemUserBinding
import si.development.ahill.beeniusdemo.domain.models.User

/**
 * Created by Andraž Hribar on 8. 11. 2019.
 * andraz.hribar@gmail.com
 */
class UsersAdapter(
    val context: Context,
    val view: UsersContract.View
) : RecyclerView.Adapter<UsersAdapter.UsersViewHolder>() {

    var data: List<User> = listOf()
        set(value) {
            val diffResult: DiffUtil.DiffResult =
                DiffUtil.calculateDiff(UserItemDiffCallback(field, value))
            field = value
            diffResult.dispatchUpdatesTo(this)
        }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): UsersViewHolder {
        val itemUserBinding: ItemUserBinding = DataBindingUtil.inflate(
            LayoutInflater.from(context),
            R.layout.item_user,
            parent,
            false
        )
        return UsersViewHolder(itemUserBinding)
    }

    override fun getItemCount(): Int =
        data.size

    override fun onBindViewHolder(holder: UsersViewHolder, position: Int) {
        holder.itemUserBinding.user = data[position]
        holder.itemUserBinding.actions = view
    }

    class UserItemDiffCallback(
        private var oldList: List<User>,
        private var newList: List<User>
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

    class UsersViewHolder(
        val itemUserBinding: ItemUserBinding
    ) : RecyclerView.ViewHolder(itemUserBinding.root)
}