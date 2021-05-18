package com.example.moviecatalogue.viewModel

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.example.moviecatalogue.data.entity.DetailEntity
import com.example.moviecatalogue.data.remote.repository.DataRepository

class DetailViewModel(private val mDataRepository: DataRepository): ViewModel() {
    companion object{
        const val TV_SHOW_DETAIL = "tvShowDetail"
        const val MOVIE_DETAIL = "movieDetail"
    }
    private lateinit var detailEntity: LiveData<DetailEntity>

    fun setMovieDetail(movieId: String, detailId: String){
        when(detailId){
            TV_SHOW_DETAIL->{
                detailEntity = mDataRepository.getMovieDetail(movieId)
            }
            MOVIE_DETAIL->{
                detailEntity = mDataRepository.getTvDetail(movieId)
            }
        }
    }

    fun getMovieDetail() = detailEntity
}