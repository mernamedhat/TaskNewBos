package com.example.taskbos.ui.home

import android.annotation.SuppressLint
import android.os.Build
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.annotation.RequiresApi
import androidx.recyclerview.widget.RecyclerView
import com.example.taskbos.databinding.AlbumsItemBinding

import com.example.taskbos.model.AlbumsResponse


class AlbumAdapter(
    var AlbumsList: List<AlbumsResponse>,
    ItemClickListener: OnItemClickListener?,


    ) : RecyclerView.Adapter<AlbumAdapter.ViewHolder>() {

    private lateinit var binding: AlbumsItemBinding
    private var listener: OnItemClickListener


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        binding = AlbumsItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder()
    }

    init {
        this.listener = ItemClickListener!!
    }

    interface OnItemClickListener {
        fun onItemClick(position: Int, item: AlbumsResponse)


    }

    override fun getItemCount() = AlbumsList.size

    @RequiresApi(Build.VERSION_CODES.Q)
    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.setData(AlbumsList[position], position)
        holder.setIsRecyclable(false)
    }

    inner class ViewHolder : RecyclerView.ViewHolder(binding.root) {
        @SuppressLint("QueryPermissionsNeeded")
        @RequiresApi(Build.VERSION_CODES.Q)
        fun setData(item: AlbumsResponse, position: Int) {
            binding.apply {
                root.setOnClickListener {

                    listener.onItemClick(position, item)
                }

                name.text = item.title


            }
        }
    }


}
