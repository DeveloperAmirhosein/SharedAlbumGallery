package com.kiliaro.project.publicpackage.retrofit

import com.kiliaro.project.publicpackage.retrofit.Service.Companion.API_URL
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory

object RetrofitSingleTon {

    private fun retrofit(baseUrl:String): Retrofit {
        return Retrofit.Builder()
            .addConverterFactory(MoshiConverterFactory.create())
            .baseUrl(baseUrl)
            .build()
    }

    val service: Service by lazy {
        retrofit(API_URL).create(Service::class.java)
    }

}