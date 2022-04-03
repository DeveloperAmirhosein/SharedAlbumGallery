package com.kiliaro.project.publicpackage.imageloader

import android.graphics.Bitmap
import android.net.Uri
import android.widget.ImageView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.MultiTransformation
import com.bumptech.glide.load.Transformation
import com.bumptech.glide.load.resource.bitmap.FitCenter
import com.bumptech.glide.load.resource.bitmap.RoundedCorners
import com.kiliaro.project.publicpackage.utils.dp

object ImageLoader {


    private const val SERVER_WIDTH = "w"
    private const val SERVER_HEIGHT = "h"
    private const val SERVER_RESIZE_MODE = "m"

    fun load(
        url: String,
        imageView: ImageView,
        width: Int?,
        height: Int?,
        serverResizeMode: ServerResizeMode,
        transformationType: Transformation<Bitmap>
    ) {
        val modifiedUrI = Uri.parse(url)
            .buildUpon()
            .apply {
                width?.let {
                    appendQueryParameter(SERVER_WIDTH, it.toString())
                }
                height?.let {
                    appendQueryParameter(SERVER_HEIGHT, it.toString())
                }
                appendQueryParameter(SERVER_RESIZE_MODE, serverResizeMode.modeName)
            }.build()

        Glide
            .with(imageView.context)
            .load(modifiedUrI)
            .transform(transformationType)
            .into(imageView)
    }

}

// this should be moved to dimens
val DEFAULT_BORDER_RADIUS = 10.dp

fun ImageView.loadImage(
    url: String,
    width: Int? = null,
    height: Int? = null,
    serverResizeMode: ServerResizeMode = ServerResizeMode.Crop,
    transformationType: Transformation<Bitmap> = MultiTransformation(
        FitCenter(),
        RoundedCorners(DEFAULT_BORDER_RADIUS)
    )
) {
    ImageLoader.load(url, this, width, height, serverResizeMode, transformationType)
}