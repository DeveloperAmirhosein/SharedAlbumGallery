package com.kiliaro.project.gallery

import androidx.core.view.doOnLayout
import androidx.recyclerview.widget.RecyclerView
import com.kiliaro.project.databinding.ItemPhotoGridBinding
import com.kiliaro.project.publicpackage.OnItemClickListener
import com.kiliaro.project.publicpackage.entities.PhotoEntity
import com.kiliaro.project.publicpackage.imageloader.loadImage
import com.kiliaro.project.publicpackage.utils.byteIntToMbString

class GalleryImageViewHolder(
    private val binding: ItemPhotoGridBinding,
    private val onItemClickListener: OnItemClickListener<PhotoEntity>
) : RecyclerView.ViewHolder(binding.root) {
    private lateinit var photoEntity: PhotoEntity

    init {
        itemView.setOnClickListener {
            onItemClickListener.onClick(
                photoEntity,
                adapterPosition,
                itemView
            )
        }
    }
    fun bind(photoEntity: PhotoEntity) {
        this.photoEntity = photoEntity
        photoEntity.thumbnailUrl?.apply {
            binding.image.doOnLayout {
                binding.image.loadImage(this, width = it.width, height = it.height)
            }
        }
        photoEntity.size?.let {
            binding.sizeOfImage.text = it.byteIntToMbString()
        }
    }

}