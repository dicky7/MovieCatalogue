package com.example.moviecatalogue.viewModel.tvView

import com.example.moviecatalogue.viewModel.TvViewModel
import org.junit.Test

import org.junit.Assert.*
import org.junit.Before

class TvViewModelTest {
    private lateinit var tvViewModel: TvViewModel

    //Setup initialize TvModel
    @Before
    fun setUp(){
        tvViewModel = TvViewModel()
    }

    //test getListTV
    @Test
    fun getTvModel() {
        val tvShow = tvViewModel.getTvModel()
        assertNotNull(tvShow)
        assertEquals(10, tvShow.size)
    }
}