package com.kiliaro.project.publicpackage.retrofit

import com.kiliaro.project.publicpackage.entities.PhotoEntity
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path

interface Service {

    @GET("/shared/{sharedkey}/media")
    fun getSharedAlbum(
        @Path("sharedkey") sharedKey: String
    ): Call<List<PhotoEntity>>

    companion object {
        const val API_URL = "https://api1.kiliaro.com/"
    }
}