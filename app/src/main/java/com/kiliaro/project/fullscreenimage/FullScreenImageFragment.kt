package com.kiliaro.project.fullscreenimage

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import com.kiliaro.project.R
import com.kiliaro.project.publicpackage.Constants.INTENT_PHOTO_ENTITY
import com.kiliaro.project.publicpackage.entities.PhotoEntity

class FullScreenImageFragment: Fragment(R.layout.fragment_gallery) {

    // this should be changed in future this fragment should be able to load an image only with
    // a URL
    private lateinit var photoEntity : PhotoEntity

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        handleIntent()
    }

    private fun handleIntent() {
        photoEntity = arguments?.getParcelable(INTENT_PHOTO_ENTITY)!!
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
    }
}