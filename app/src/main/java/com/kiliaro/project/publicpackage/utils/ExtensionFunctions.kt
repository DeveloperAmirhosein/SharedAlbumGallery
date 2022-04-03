package com.kiliaro.project.publicpackage.utils

import android.content.res.Resources

/** dp to px*/
val Int.dp: Int
    get() = (this * Resources.getSystem().displayMetrics.density).toInt()