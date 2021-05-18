package com.example.moviecatalogue.viewModel

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.example.moviecatalogue.data.entity.TvEntity
import com.example.moviecatalogue.data.remote.repository.DataRepository

class TvViewModel(private val mDataRepository: DataRepository): ViewModel() {
    fun getTvModel(): LiveData<List<TvEntity>> = mDataRepository.getTv()
}