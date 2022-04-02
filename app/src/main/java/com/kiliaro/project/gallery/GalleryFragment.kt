package com.kiliaro.project.gallery

import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.kiliaro.project.R
import com.kiliaro.project.publicpackage.entities.PhotoEntity
import com.kiliaro.project.publicpackage.retrofit.Error
import com.kiliaro.project.publicpackage.retrofit.Progress
import com.kiliaro.project.publicpackage.retrofit.Result
import com.kiliaro.project.publicpackage.retrofit.Success

class GalleryFragment : Fragment(R.layout.fragment_gallery) {
    // we can pass this key to this fragment so this gallery fragment can be
    // a general fragment that shows any album in future
    private val sharedKey: String = "djlCbGusTJamg_ca4axEVw"

    private val viewModel: GalleryViewModel by viewModels {
        GalleryViewModelFactory(SharedAlbumRepository(sharedKey))
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        observe()
        viewModel.getSharedAlbum()
    }

    private fun observe() {
        viewModel.sharedAlbumLiveData.observe(viewLifecycleOwner, {
            handleAlbumLiveData(it)
        })
    }

    private fun handleAlbumLiveData(result: Result<List<PhotoEntity>>?) {
        when (result) {
            is Success -> {
                Log.e("apptest","in success")
            }
            is Progress -> {
                Log.e("apptest","in progress")
            }
            is Error -> {
                Log.e("apptest","in Error")
            }
        }
    }
}