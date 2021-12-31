package com.te.photochallenge.view.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.navigation.Navigation
import androidx.recyclerview.widget.RecyclerView
import com.te.photochallenge.databinding.ItemPhotoBinding
import com.te.photochallenge.model.Photo
import com.te.photochallenge.utils.getProgressDrawable
import com.te.photochallenge.utils.loadImage
import com.te.photochallenge.view.PhotoListFragmentDirections
import org.koin.core.component.KoinComponent

class PhotoListAdapter(val list: ArrayList<Photo>) :
    RecyclerView.Adapter<PhotoListAdapter.PhotoVH>(), KoinComponent {


    fun updateList(newList: List<Photo>) {
        list.clear()
        list.addAll(newList)
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PhotoVH {
        return PhotoVH(
            ItemPhotoBinding.inflate(
                LayoutInflater.from(parent.context),
                parent, false
            )
        )
    }

    override fun onBindViewHolder(holder: PhotoVH, position: Int) {

        holder.view.apply {
            "Photo title: ${list[position].title.toString()}".also { photoTitleId.text = it }
            "Photo album id: ${list[position].albumId.toString()}".also { photoAlbumId.text = it }
            "Photo id: ${list[position].id.toString()}".also { photoId.text = it }

            photoPlaceholder.loadImage(
                list[position].thumbnailUrl,
                getProgressDrawable(holder.itemView.context)
            )

            holder.itemView.setOnClickListener {
                val action =
                    PhotoListFragmentDirections.actionListToPhotoFragment(list[position].url)
                Navigation.findNavController(it).navigate(action)
            }
        }
    }

    override fun getItemCount(): Int = list.size

    class PhotoVH(var view: ItemPhotoBinding) : RecyclerView.ViewHolder(view.root)
}