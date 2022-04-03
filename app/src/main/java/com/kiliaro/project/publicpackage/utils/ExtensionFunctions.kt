package com.kiliaro.project.publicpackage.utils

import android.content.res.Resources
import androidx.fragment.app.Fragment
import androidx.lifecycle.Lifecycle
import java.text.SimpleDateFormat
import java.util.*

/** dp to px*/
val Int.dp: Int
    get() = (this * Resources.getSystem().displayMetrics.density).toInt()

fun String.toProperDateFormat(): String? {
    val inputFormat = SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss'Z'", Locale.ENGLISH)
    val outputFormat = SimpleDateFormat("dd/MM/yyyy", Locale.ENGLISH)
    var date: Date? = null
    try {
        date = inputFormat.parse(this)
    } catch (e: Throwable) {
    }
    date ?: return null
    return outputFormat.format(date)
}

fun Fragment.doIfViewIsReady(runnable: Runnable) {
    var isViewAvailable = false
    try {
        // if viewLifecycleOwner.lifecycle.currentState.isAtLeast(Lifecycle.State.CREATED)
        // throws exception or if it is false, it means the view of fragment is not ready or destroyed
        isViewAvailable =
            viewLifecycleOwner.lifecycle.currentState.isAtLeast(Lifecycle.State.CREATED)
    } catch (ignored: Exception) {
    }
    if (isViewAvailable) runnable.run()
}