package com.kiliaro.project.gallery

import androidx.recyclerview.widget.RecyclerView
import com.kiliaro.project.databinding.ItemPhotoGridBinding
import com.kiliaro.project.publicpackage.entities.PhotoEntity
import com.kiliaro.project.publicpackage.imageloader.loadImage
import com.kiliaro.project.publicpackage.utils.dp
import kotlin.math.max

class GalleryImageViewHolder(private val binding: ItemPhotoGridBinding) :
    RecyclerView.ViewHolder(binding.root) {
    fun bind(photoEntity: PhotoEntity) {
        photoEntity.thumbnailUrl?.let {

            // we use post{} here because if we do not use it
            // the getWidth() and height() methods are calculated before the time
            // in which view is drawn..
            binding.image.post {
                // in some rare cases(few special phones) post{} does not work so we use
                // a minSize to avoid downloading big Image size
                // there are other choices that work on all phones but
                // this part is not main purpose here
                val minSize = 60.dp
                val width = max(minSize, itemView.width)
                val height = max(minSize, itemView.height)
                // pass width and height to avoid downloading big image
                binding.image.loadImage(it, width = width, height = height)
            }
        }
    }

}