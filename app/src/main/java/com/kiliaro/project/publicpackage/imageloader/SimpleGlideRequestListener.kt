package com.kiliaro.project.publicpackage.imageloader

interface SimpleGlideRequestListener {
    fun onResourceReady()
    fun onResourceFailed(message: String?)
}