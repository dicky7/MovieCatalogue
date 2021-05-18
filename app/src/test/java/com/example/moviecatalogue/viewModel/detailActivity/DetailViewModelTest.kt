package com.example.moviecatalogue.viewModel.detailActivity

import com.example.moviecatalogue.DataMovie
import com.example.moviecatalogue.viewModel.DetailViewModel
import org.junit.Test

import org.junit.Assert.*
import org.junit.Before

class DetailViewModelTest {
    private lateinit var detailViewModel: DetailViewModel
    private val dataMovie = DataMovie.generateDataMovie()[0]
    private val dataMovieId = dataMovie.id

    private val dataTv = DataMovie.generateDataTv()[0]
    private val dataTvId = dataTv.id

    //SetUp initialize MovieDetail
    @Before
    fun setUpMovie(){
        detailViewModel = DetailViewModel()
        detailViewModel.setMovieDetail(dataMovieId,"movieDetail")
    }

    //Get Data Movie Detail
    @Test
    fun getMovieDetail() {

        detailViewModel.setMovieDetail(dataMovie.id,"movieDetail")
        val movieModel = detailViewModel.getMovieDetail()
        assertNotNull(movieModel)
        assertEquals(dataMovie.id, movieModel.id)
        assertEquals(dataMovie.poster, movieModel.poster)
        assertEquals(dataMovie.title, movieModel.title)
        assertEquals(dataMovie.desc, movieModel.desc)
        assertEquals(dataMovie.genre, movieModel.genre)
        assertEquals(dataMovie.realese, movieModel.realese)
    }

    //SetUp initialize TvShowDetail
    @Before
    fun setTv(){
        detailViewModel = DetailViewModel()
        detailViewModel.setMovieDetail(dataTvId,"tvShowDetail")
    }

    //Get Data TvShow Detail
    @Test
    fun getTvDetail() {
        detailViewModel.setMovieDetail(dataTv.id,"tvShowDetail")
        val tvModel = detailViewModel.getMovieDetail()
        assertNotNull(tvModel)
        assertEquals(dataTv.id, tvModel.id)
        assertEquals(dataTv.poster, tvModel.poster)
        assertEquals(dataTv.title, tvModel.title)
        assertEquals(dataTv.desc, tvModel.desc)
        assertEquals(dataTv.genre, tvModel.genre)
        assertEquals(dataTv.realese, tvModel.realese)
    }
}