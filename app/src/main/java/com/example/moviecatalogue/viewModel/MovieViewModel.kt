package com.example.moviecatalogue.viewModel

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.example.moviecatalogue.data.entity.MovieEntity
import com.example.moviecatalogue.data.remote.repository.DataRepository

class MovieViewModel(private val mDataRepository: DataRepository): ViewModel() {
    fun getMovieModel():LiveData<List<MovieEntity>> = mDataRepository.getMovie()
}