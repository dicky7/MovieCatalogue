package com.example.moviecatalogue.viewModel

import android.content.Context
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.moviecatalogue.data.remote.repository.DataRepository
import com.example.moviecatalogue.di.Injection

class ViewModelFactory private constructor(private val mDataRepository: DataRepository): ViewModelProvider.NewInstanceFactory() {
    companion object{
        @Volatile
        private var instance: ViewModelFactory? = null
        fun getInstance (context: Context): ViewModelFactory =
            instance?: synchronized(this){
                instance?: ViewModelFactory(Injection.provideRepository(context)).apply {
                    instance = this
                }
            }
    }

    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return when{
            modelClass.isAssignableFrom(MovieViewModel::class.java)->{
                MovieViewModel(mDataRepository) as T
            }
            modelClass.isAssignableFrom(TvViewModel::class.java)->{
                TvViewModel(mDataRepository) as T
            }
            modelClass.isAssignableFrom(DetailViewModel::class.java)->{
                DetailViewModel(mDataRepository) as T
            }
            else -> throw Throwable("Unknown ViewModel class: " + modelClass.name)
        }
    }
}