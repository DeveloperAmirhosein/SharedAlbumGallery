package com.kiliaro.project.publicpackage

import android.view.View

interface OnItemClickListener<T> {
    fun onClick(item: T, position: Int, clickedView: View)
}