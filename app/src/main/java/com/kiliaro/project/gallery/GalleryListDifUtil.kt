package com.kiliaro.project.gallery

import androidx.recyclerview.widget.DiffUtil
import com.kiliaro.project.publicpackage.entities.PhotoEntity

class GalleryListDifUtil : DiffUtil.Callback() {
    private val oldList: MutableList<PhotoEntity> = ArrayList()
    private val newList: MutableList<PhotoEntity> = ArrayList()

    fun setData(newList: List<PhotoEntity>) {
        oldList.clear()
        oldList.addAll(this.newList)
        this.newList.clear()
        this.newList.addAll(newList)
    }

    override fun getOldListSize(): Int = oldList.size

    override fun getNewListSize(): Int = newList.size

    override fun areItemsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
        return oldList[oldItemPosition].id == newList[newItemPosition].id
    }

    override fun areContentsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
        return oldList[oldItemPosition].thumbnailUrl == newList[newItemPosition].thumbnailUrl
    }
}