package com.kiliaro.project.gallery

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.kiliaro.project.R
import com.kiliaro.project.databinding.ItemPhotoGridBinding
import com.kiliaro.project.publicpackage.OnItemClickListener
import com.kiliaro.project.publicpackage.entities.PhotoEntity

class GalleryAdapter(private val onItemClickListener: OnItemClickListener<PhotoEntity>) :
    RecyclerView.Adapter<GalleryImageViewHolder>() {
    private val list: MutableList<PhotoEntity> = ArrayList()
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): GalleryImageViewHolder {
        val binding = ItemPhotoGridBinding.bind(
            LayoutInflater.from(parent.context).inflate(R.layout.item_photo_grid, parent, false)
        )
        return GalleryImageViewHolder(binding,onItemClickListener)
    }

    override fun onBindViewHolder(holder: GalleryImageViewHolder, position: Int) {
        holder.bind(list[position])
    }

    override fun getItemCount(): Int = list.size

    fun setData(photoList: List<PhotoEntity>) {
        list.clear()
        list.addAll(photoList)
        notifyDataSetChanged()
    }

}