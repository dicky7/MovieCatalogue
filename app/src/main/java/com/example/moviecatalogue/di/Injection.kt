package com.example.moviecatalogue.di

import android.content.Context
import com.example.moviecatalogue.data.remote.dataSource.RemoteDataSource
import com.example.moviecatalogue.data.remote.repository.DataRepository

object Injection {
    fun provideRepository(context: Context): DataRepository{
        val remoteDataSource = RemoteDataSource.getInstance()
        return DataRepository.getInstance(remoteDataSource)
    }
}