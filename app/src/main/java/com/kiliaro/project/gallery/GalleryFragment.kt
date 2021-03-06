package com.kiliaro.project.gallery

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.NavController
import androidx.navigation.Navigation
import androidx.recyclerview.widget.GridLayoutManager
import com.kiliaro.project.R
import com.kiliaro.project.databinding.FragmentGalleryBinding
import com.kiliaro.project.publicpackage.GlobalConstants.INTENT_PHOTO_ENTITY
import com.kiliaro.project.publicpackage.OnItemClickListener
import com.kiliaro.project.publicpackage.entities.PhotoEntity
import com.kiliaro.project.publicpackage.retrofit.Error
import com.kiliaro.project.publicpackage.retrofit.Progress
import com.kiliaro.project.publicpackage.retrofit.Result
import com.kiliaro.project.publicpackage.retrofit.Success
import com.kiliaro.project.publicpackage.utils.disable
import com.kiliaro.project.publicpackage.utils.enable
import com.kiliaro.project.publicpackage.utils.hide
import com.kiliaro.project.publicpackage.utils.show

class GalleryFragment : Fragment(R.layout.fragment_gallery) {
    // we can pass this key to this fragment so this gallery fragment can be
    // a general fragment that shows any album in future
    private val sharedKey: String = "djlCbGusTJamg_ca4axEVw"
    private var _binding: FragmentGalleryBinding? = null
    private val binding get() = _binding!!
    private lateinit var navController: NavController
    private val spanCount = 3

    private val viewModel: GalleryViewModel by viewModels {
        GalleryViewModelFactory(DefaultSharedAlbumRepository(sharedKey))
    }

    private val adapter by lazy {
        GalleryAdapter(object : OnItemClickListener<PhotoEntity> {
            override fun onClick(item: PhotoEntity, position: Int, clickedView: View) {
                val bundle = Bundle()
                bundle.putParcelable(INTENT_PHOTO_ENTITY, item)
                navController.navigate(
                    R.id.action_galleryFragment_to_fullScreenImageFragment,
                    bundle
                )
            }
        })
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        _binding = FragmentGalleryBinding.bind(view)
        navController = Navigation.findNavController(view)
        observe()
        binding.imagesList.adapter = adapter
        binding.imagesList.layoutManager = GridLayoutManager(requireActivity(), spanCount)
        binding.swipeRefreshLayout.setOnRefreshListener {
            viewModel.getRefreshedSharedAlbum()
        }
        binding.errorView.retryButton.setOnClickListener {
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
                binding.errorView.rootView.hide()
            }
            is Progress -> {
                binding.progressBar.show()
                binding.errorView.retryButton.disable()
            }
            is Error -> {
                binding.progressBar.hide()
                binding.swipeRefreshLayout.isRefreshing = false
                binding.errorView.errorText.text = result.errorMessage
                binding.errorView.rootView.show()
                binding.errorView.retryButton.enable()
            }
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}