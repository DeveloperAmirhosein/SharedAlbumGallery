package com.kiliaro.project.fullscreenimage

import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.fragment.app.Fragment
import com.kiliaro.project.R
import com.kiliaro.project.databinding.FragmentFullScreenImageBinding
import com.kiliaro.project.publicpackage.Constants.INTENT_PHOTO_ENTITY
import com.kiliaro.project.publicpackage.entities.PhotoEntity
import com.kiliaro.project.publicpackage.imageloader.ServerResizeMode
import com.kiliaro.project.publicpackage.imageloader.SimpleGlideRequestListener
import com.kiliaro.project.publicpackage.imageloader.loadImage
import com.kiliaro.project.publicpackage.utils.doIfViewIsReady
import com.kiliaro.project.publicpackage.utils.hide
import com.kiliaro.project.publicpackage.utils.show

class FullScreenImageFragment : Fragment(R.layout.fragment_full_screen_image) {

    private lateinit var photoEntity: PhotoEntity
    private var _binding: FragmentFullScreenImageBinding? = null
    private val binding: FragmentFullScreenImageBinding get() = _binding!!

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        handleIntent()
    }

    private fun handleIntent() {
        photoEntity = arguments?.getParcelable(INTENT_PHOTO_ENTITY)!!
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        _binding = FragmentFullScreenImageBinding.bind(view)
        binding.progressBar.show()
        binding.backButton.setOnClickListener { requireActivity().onBackPressed() }
        binding.mainImage.loadImage(
            url = photoEntity.thumbnailUrl,
            transformationType = null,
            placeHolder = null,
            serverResizeMode = ServerResizeMode.BoundingBox,
            requestListener = object : SimpleGlideRequestListener {
                override fun onResourceReady() {
                    doIfViewIsReady {
                        binding.progressBar.hide()
                    }
                }

                override fun onResourceFailed(message: String?) {
                    doIfViewIsReady {
                        Toast.makeText(requireActivity(), message, Toast.LENGTH_SHORT)
                            .show()
                        binding.progressBar.hide()
                    }
                }
            })

    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}