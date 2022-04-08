package com.kiliaro.project.gallery

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.kiliaro.project.publicpackage.entities.PhotoEntity
import com.kiliaro.project.publicpackage.retrofit.*
import com.kiliaro.project.publicpackage.utils.invalidateCache
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class SharedAlbumRepository(private val sharedKey: String) {
    private val sharedAlbumLiveData: MutableLiveData<Result<List<PhotoEntity>>> = MutableLiveData()


    fun getSharedAlbumLiveData(): LiveData<Result<List<PhotoEntity>>> = sharedAlbumLiveData

    fun getSharedAlbum() {
        sharedAlbumLiveData.postValue(Progress())
        RetrofitSingleTon.service.getSharedAlbum(sharedKey).enqueue(
            object : Callback<List<PhotoEntity>> {
                override fun onResponse(
                    call: Call<List<PhotoEntity>>,
                    response: Response<List<PhotoEntity>>
                ) {
                    response.body()?.let {
                        sharedAlbumLiveData.postValue(Success(it))
                    } ?: run {
                        sharedAlbumLiveData.postValue(Error("Response is null"))
                    }
                }

                override fun onFailure(call: Call<List<PhotoEntity>>, throwable: Throwable) {
                    sharedAlbumLiveData.postValue(
                        Error(
                            "Failed to get data from server",
                            throwable
                        )
                    )
                }
            }
        )
    }

    fun getRefreshedSharedAlbums() {
        RetrofitSingleTon.service.getSharedAlbum(sharedKey).invalidateCache()
        getSharedAlbum()
    }

}