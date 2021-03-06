package com.kiliaro.project.publicpackage.retrofit

import android.content.Context
import android.net.ConnectivityManager
import android.net.NetworkCapabilities
import android.os.Build
import com.kiliaro.project.main.MyApplication
import com.kiliaro.project.publicpackage.retrofit.Service.Companion.API_URL
import okhttp3.Cache
import okhttp3.CacheControl
import okhttp3.OkHttpClient
import retrofit2.Call
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import java.util.concurrent.TimeUnit

object NetworkManager {
    private const val HEADER_PRAGMA = "Pragma"
    private const val CACHE_MAX_AGE_DAYS = 7
    private const val CACHE_VALID_AGE_SECONDS = 60
    private const val TIMEOUT_SECONDS = 10L
    private const val CACHE_SIZE = 10 * 1024 * 1024L // 10 MB
    private val cache = Cache(MyApplication.getAppContext().cacheDir, CACHE_SIZE)

    private fun retrofit(baseUrl: String): Retrofit {
        return Retrofit.Builder()
            .addConverterFactory(MoshiConverterFactory.create())
            .client(okHttpClient)
            .baseUrl(baseUrl)
            .build()
    }


    val service: Service by lazy {
        retrofit(API_URL).create(Service::class.java)
    }


    private val okHttpClient: OkHttpClient = OkHttpClient().newBuilder()
        .cache(cache)
        .connectTimeout(TIMEOUT_SECONDS, TimeUnit.SECONDS)
        .readTimeout(TIMEOUT_SECONDS, TimeUnit.SECONDS)
        .addInterceptor { chain ->
            chain.proceed(
                chain.request().newBuilder()
                    .removeHeader(HEADER_PRAGMA)
                    .cacheControl(
                        CacheControl.Builder()
                            .apply {
                                if (isNetworkAvailable())
                                    maxAge(CACHE_VALID_AGE_SECONDS, TimeUnit.SECONDS)
                                else maxStale(CACHE_MAX_AGE_DAYS, TimeUnit.DAYS)
                            }.build()
                    ).build()
            )
        }.build()

    fun invalidateCacheForAnSpecificCall(call: Call<*>) {
        val url = call.request().url().toString()
        val urlIterator = cache.urls()
        while (urlIterator.hasNext()) {
            if (urlIterator.next().startsWith(url)) {
                urlIterator.remove()
            }
        }
    }

    fun isNetworkAvailable(): Boolean {
        val context = MyApplication.getAppContext()
        (context.getSystemService(Context.CONNECTIVITY_SERVICE) as? ConnectivityManager)?.apply {
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.Q) getNetworkCapabilities(activeNetwork)?.takeIf {
                it.hasTransport(NetworkCapabilities.TRANSPORT_CELLULAR) ||
                        it.hasTransport(NetworkCapabilities.TRANSPORT_WIFI) ||
                        it.hasTransport(NetworkCapabilities.TRANSPORT_ETHERNET)
            }?.apply { return true }
            else return (activeNetworkInfo?.isConnected == true)
        }
        return false
    }

}