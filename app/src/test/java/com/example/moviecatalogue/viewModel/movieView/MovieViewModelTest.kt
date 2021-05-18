package com.example.moviecatalogue.viewModel.movieView

import com.example.moviecatalogue.viewModel.MovieViewModel
import org.junit.Test

import org.junit.Assert.*
import org.junit.Before

class MovieViewModelTest {
    private lateinit var movieViewModel: MovieViewModel

    //Setup initialize MovieModel
    @Before
    fun setUp(){
        movieViewModel = MovieViewModel()
    }

    //test getListMovie
    @Test
    fun getMovieModel() {
        val movie = movieViewModel.getMovieModel()
        assertNotNull(movie)
        assertEquals(10, movie.size)
    }
}