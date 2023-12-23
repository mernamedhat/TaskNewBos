package com.example.taskbos.ui.albums

import android.annotation.SuppressLint
import android.content.Context
import android.os.Build
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import androidx.annotation.RequiresApi
import androidx.recyclerview.widget.RecyclerView

import com.example.taskbos.databinding.GridviewItemBinding
import com.example.taskbos.model.PhotosResponse


import com.squareup.picasso.Picasso

class GridAlbumsAdapter(
    var AlbumsList: List<PhotosResponse>,
) : BaseAdapter() {


    fun submitList(newList: List<PhotosResponse>) {
        AlbumsList = newList
        notifyDataSetChanged()
    }

    fun refreshList(newList: List<PhotosResponse>) {
        AlbumsList = newList
        notifyDataSetChanged()
    }
    override fun getCount(): Int = AlbumsList.size

    override fun getItem(position: Int): Any = AlbumsList[position]

    override fun getItemId(position: Int): Long = position.toLong()

    override fun getView(position: Int, convertView: View?, parent: ViewGroup?): View {
        val binding =
            GridviewItemBinding.inflate(LayoutInflater.from(parent?.context), parent, false)

Log.d("backkkk",AlbumsList[position].url)
        Picasso.get().load(AlbumsList[position].url).into(binding.idphoto)


        return binding.root
    }
}
