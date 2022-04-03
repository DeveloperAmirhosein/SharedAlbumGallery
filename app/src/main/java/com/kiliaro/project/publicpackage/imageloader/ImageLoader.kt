package com.kiliaro.project.publicpackage.imageloader

import android.annotation.SuppressLint
import android.graphics.Bitmap
import android.graphics.drawable.Drawable
import android.net.Uri
import android.widget.ImageView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.DataSource
import com.bumptech.glide.load.MultiTransformation
import com.bumptech.glide.load.Transformation
import com.bumptech.glide.load.engine.GlideException
import com.bumptech.glide.load.resource.bitmap.FitCenter
import com.bumptech.glide.load.resource.bitmap.RoundedCorners
import com.bumptech.glide.request.RequestListener
import com.bumptech.glide.request.target.Target
import com.kiliaro.project.R
import com.kiliaro.project.publicpackage.utils.dp

object ImageLoader {


    private const val SERVER_WIDTH = "w"
    private const val SERVER_HEIGHT = "h"
    private const val SERVER_RESIZE_MODE = "m"

    @SuppressLint("CheckResult")
    fun load(
        url: String?,
        imageView: ImageView,
        width: Int?,
        height: Int?,
        placeHolder: Int? = null,
        serverResizeMode: ServerResizeMode,
        requestListener: SimpleGlideRequestListener? = null,
        transformationType: Transformation<Bitmap>? = null
    ) {
        url ?: return
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

        Glide.with(imageView.context).load(modifiedUrI).apply {
            transformationType?.let { transform(transformationType) }
            requestListener?.let {
                addListener(object : RequestListener<Drawable> {
                    override fun onLoadFailed(
                        e: GlideException?,
                        model: Any?,
                        target: Target<Drawable>?,
                        isFirstResource: Boolean
                    ): Boolean {
                        it.onResourceFailed(e?.message)
                        return false
                    }

                    override fun onResourceReady(
                        resource: Drawable?,
                        model: Any?,
                        target: Target<Drawable>?,
                        dataSource: DataSource?,
                        isFirstResource: Boolean
                    ): Boolean {
                        it.onResourceReady()
                        return false
                    }
                })
            }
            placeHolder?.let { placeholder(it) }
            into(imageView)
        }

    }

}

// this should be moved to dimens
val DEFAULT_BORDER_RADIUS = 10.dp

fun ImageView.loadImage(
    url: String?,
    width: Int? = null,
    height: Int? = null,
    placeHolder: Int? = R.drawable.ic_image_place_holder,
    serverResizeMode: ServerResizeMode = ServerResizeMode.Crop,
    requestListener: SimpleGlideRequestListener? = null,
    transformationType: Transformation<Bitmap>? = MultiTransformation(
        FitCenter(),
        RoundedCorners(DEFAULT_BORDER_RADIUS)
    )
) {

    ImageLoader.load(
        url,
        this,
        width,
        height,
        placeHolder,
        serverResizeMode,
        requestListener,
        transformationType
    )
}