package com.kiliaro.project.gallery

import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.NavController
import androidx.navigation.Navigation
import androidx.recyclerview.widget.GridLayoutManager
import com.kiliaro.project.R
import com.kiliaro.project.databinding.FragmentGalleryBinding
import com.kiliaro.project.publicpackage.Constants.INTENT_PHOTO_ENTITY
import com.kiliaro.project.publicpackage.OnItemClickListener
import com.kiliaro.project.publicpackage.entities.PhotoEntity
import com.kiliaro.project.publicpackage.retrofit.Error
import com.kiliaro.project.publicpackage.retrofit.Progress
import com.kiliaro.project.publicpackage.retrofit.Result
import com.kiliaro.project.publicpackage.retrofit.Success

class GalleryFragment : Fragment(R.layout.fragment_gallery) {
    // we can pass this key to this fragment so this gallery fragment can be
    // a general fragment that shows any album in future
    private val sharedKey: String = "djlCbGusTJamg_ca4axEVw"
    private var _binding: FragmentGalleryBinding? = null
    private val binding get() = _binding!!
    private val adapter by lazy {
        GalleryAdapter(object : OnItemClickListener<PhotoEntity> {
            override fun onClick(item: PhotoEntity, position: Int, clickedView: View) {
                val bundle = Bundle()
                bundle.putParcelable(INTENT_PHOTO_ENTITY, item)
                navController?.navigate(
                    R.id.action_galleryFragment_to_fullScreenImageFragment,
                    bundle
                )
            }
        })
    }
    private val viewModel: GalleryViewModel by viewModels {
        GalleryViewModelFactory(SharedAlbumRepository(sharedKey))
    }
    private var navController: NavController? = null

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        _binding = FragmentGalleryBinding.bind(view)
        navController = Navigation.findNavController(view)
        observe()
        binding.imagesList.adapter = adapter
        binding.imagesList.layoutManager = GridLayoutManager(requireActivity(), 3)
        binding.swipeRefreshLayout.setOnRefreshListener {
            viewModel.getRefreshedSharedAlbum()
        }
    }

    private fun observe() {
        viewModel.sharedAlbumLiveData.observe(viewLifecycleOwner) {
            handleResult(it)
        }
    }

    private fun handleResult(result: Result<List<PhotoEntity>>?) {
        when (result) {
            is Success -> {
                binding.progressBar.hide()
                adapter.setData(result.data)
                binding.swipeRefreshLayout.isRefreshing = false
            }
            is Progress -> {
                binding.progressBar.show()
            }
            is Error -> {
                binding.progressBar.hide()
                binding.swipeRefreshLayout.isRefreshing = false
                adapter.deleteList()
                Toast.makeText(requireActivity(), result.errorMessage, Toast.LENGTH_SHORT).show()
            }
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        binding.imagesList.adapter = null
        _binding = null
    }
}