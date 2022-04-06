package com.kiliaro.project.gallery

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.kiliaro.project.publicpackage.entities.PhotoEntity
import com.kiliaro.project.publicpackage.retrofit.Result

class GalleryViewModel(private val repository: SharedAlbumRepository) : ViewModel() {
    val sharedAlbumLiveData: LiveData<Result<List<PhotoEntity>>> =
        repository.getSharedAlbumLiveData()

    init {
        getSharedAlbum()
    }

    fun getSharedAlbum() {
        repository.getSharedAlbum()
    }
}