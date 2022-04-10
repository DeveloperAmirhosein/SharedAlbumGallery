package com.kiliaro.project.gallery

import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.Mockito.verify
import org.mockito.junit.MockitoJUnitRunner

@RunWith(MockitoJUnitRunner::class)
class GalleryViewModelTest {

    @Mock
    lateinit var repository: SharedAlbumRepository

    lateinit var testViewModel: GalleryViewModel

    @Before
    fun onSetup() {
        testViewModel = GalleryViewModel(repository)
    }

    @Test
    fun `starting viewModel should call repository getSharedAlbum() method`(){
        verify(repository).getSharedAlbum()
    }

    @Test
    fun `calling getRefreshedSharedAlbum() on viewModel should call getRefreshedSharedAlbum() on repository`(){
        testViewModel.getRefreshedSharedAlbum()
        verify(repository).getRefreshedSharedAlbum()
    }

    @Test
    fun `calling sharedAlbumLiveData on viewModel should call getSharedAlbumLiveData() on repository`(){
        testViewModel.sharedAlbumLiveData
        verify(repository).getSharedAlbumLiveData()
    }



}