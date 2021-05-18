package com.example.moviecatalogue.data.remote.dataSource

import androidx.lifecycle.LiveData
import com.example.moviecatalogue.data.entity.DetailEntity
import com.example.moviecatalogue.data.entity.MovieEntity
import com.example.moviecatalogue.data.entity.TvEntity

interface DataSource {
    fun getMovie(): LiveData<List<MovieEntity>>
    fun getMovieDetail(movieId: String): LiveData<DetailEntity>
    fun getTv(): LiveData<List<TvEntity>>
    fun getTvDetail(tvId: String): LiveData<DetailEntity>
}