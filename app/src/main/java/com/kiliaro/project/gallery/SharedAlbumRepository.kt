package com.kiliaro.project.gallery

import androidx.lifecycle.LiveData
import com.kiliaro.project.publicpackage.entities.PhotoEntity
import com.kiliaro.project.publicpackage.retrofit.Result

interface SharedAlbumRepository {
    fun getSharedAlbumLiveData(): LiveData<Result<List<PhotoEntity>>>
    fun getSharedAlbum()
    fun getRefreshedSharedAlbum()
}