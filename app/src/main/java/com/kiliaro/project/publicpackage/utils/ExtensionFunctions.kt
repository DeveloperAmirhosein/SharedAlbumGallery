package com.kiliaro.project.publicpackage.utils

import android.content.res.Resources
import androidx.fragment.app.Fragment
import androidx.lifecycle.Lifecycle
import java.lang.Exception

/** dp to px*/
val Int.dp: Int
    get() = (this * Resources.getSystem().displayMetrics.density).toInt()

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