package com.kiliaro.project.gallery

import androidx.recyclerview.widget.RecyclerView
import com.kiliaro.project.databinding.ItemPhotoGridBinding
import com.kiliaro.project.publicpackage.entities.PhotoEntity
import com.kiliaro.project.publicpackage.imageloader.loadImage
import com.kiliaro.project.publicpackage.utils.dp

class GalleryImageViewHolder(private val binding: ItemPhotoGridBinding) :
    RecyclerView.ViewHolder(binding.root) {
    fun bind(photoEntity: PhotoEntity) {
        photoEntity.thumbnailUrl?.let {
            binding.image.loadImage(it,width = 50.dp,height = 50.dp)
        }
    }

}