package com.kiliaro.project.gallery

import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.kiliaro.project.R

class GalleryFragment : Fragment(R.layout.fragment_gallery) {
    val viewModel: GalleryViewModel by viewModels()
}