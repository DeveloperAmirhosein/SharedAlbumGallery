package com.kiliaro.project.publicpackage.retrofit

sealed class Result<T>
data class Success<T>(val data: T) : Result<T>()
class Progress<T> : Result<T>()
data class Error<T>(val errorMessage: String, val throwable: Throwable? = null) : Result<T>()
