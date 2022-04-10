package com.kiliaro.project.publicpackage.utils

import android.content.res.Resources
import androidx.fragment.app.Fragment
import androidx.lifecycle.Lifecycle
import com.kiliaro.project.main.MyApplication
import com.kiliaro.project.publicpackage.retrofit.NetworkManager
import retrofit2.Call
import java.text.DecimalFormat
import java.text.SimpleDateFormat
import java.util.*

val Int.dpToPx: Int
    get() = (this * Resources.getSystem().displayMetrics.density).toInt()


fun String.toMoreReadableDateFormat(): String? {
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


/** Does a runnable if the view of the fragment is created and not destroyed */
fun Fragment.doIfViewIsReady(runnable: Runnable) {
    var isViewAvailable = false
    // We use try catch here because throwing exception means that the view is not created or is destroyed
    try {
        isViewAvailable =
            viewLifecycleOwner.lifecycle.currentState.isAtLeast(Lifecycle.State.CREATED)
    } catch (ignored: Exception) {
    }
    if (isViewAvailable) runnable.run()
}


fun Int.byteIntToMbString(): String {
    if (this < 0) return "0MB"
    val decimalFormat = DecimalFormat("###.#")
    return (decimalFormat.format(this / (1024.0 * 1024.0))).toString() + "MB"
}


fun Call<*>.invalidateCache() {
    NetworkManager.invalidateCacheForAnSpecificCall(this)
}

// Returns String resource from strings.xml
fun Int.getString(): String {
    return MyApplication.getAppContext().getString(this)
}
