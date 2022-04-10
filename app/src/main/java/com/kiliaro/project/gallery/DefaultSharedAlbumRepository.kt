package com.kiliaro.project.gallery

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.kiliaro.project.R
import com.kiliaro.project.publicpackage.entities.PhotoEntity
import com.kiliaro.project.publicpackage.retrofit.*
import com.kiliaro.project.publicpackage.utils.getStringResource
import com.kiliaro.project.publicpackage.utils.invalidateCache
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class DefaultSharedAlbumRepository(private val sharedKey: String) : SharedAlbumRepository {
    private val sharedAlbumLiveData: MutableLiveData<Result<List<PhotoEntity>>> = MutableLiveData()

    override fun getSharedAlbumLiveData(): LiveData<Result<List<PhotoEntity>>> = sharedAlbumLiveData

    override fun getSharedAlbum() {
        sharedAlbumLiveData.postValue(Progress())
        NetworkManager.service.getSharedAlbum(sharedKey).enqueue(
            object : Callback<List<PhotoEntity>> {
                override fun onResponse(
                    call: Call<List<PhotoEntity>>,
                    response: Response<List<PhotoEntity>>
                ) {
                    response.body()?.let {
                        if (response.isSuccessful) sharedAlbumLiveData.postValue(Success(it))
                        else sharedAlbumLiveData.postValue(Error(R.string.general_connection_error.getStringResource()))
                    } ?: run {
                        sharedAlbumLiveData.postValue(Error(R.string.general_connection_error.getStringResource()))
                    }
                }

                override fun onFailure(call: Call<List<PhotoEntity>>, throwable: Throwable) {
                    sharedAlbumLiveData.postValue(
                        if (NetworkManager.isNetworkAvailable().not())
                            Error(R.string.internet_connection_error.getStringResource(), throwable)
                        else
                            Error(R.string.general_connection_error.getStringResource(), throwable)
                    )
                }
            }
        )
    }

    override fun getRefreshedSharedAlbum() {
        NetworkManager.service.getSharedAlbum(sharedKey).invalidateCache()
        getSharedAlbum()
    }

}