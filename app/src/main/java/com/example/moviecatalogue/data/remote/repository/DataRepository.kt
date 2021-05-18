package com.example.moviecatalogue.data.remote.repository

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.moviecatalogue.data.entity.DetailEntity
import com.example.moviecatalogue.data.entity.MovieEntity
import com.example.moviecatalogue.data.entity.TvEntity
import com.example.moviecatalogue.data.remote.dataSource.DataSource
import com.example.moviecatalogue.data.remote.dataSource.RemoteDataSource
import com.example.moviecatalogue.data.remote.response.movie.MovieDetailResponse
import com.example.moviecatalogue.data.remote.response.movie.MovieRemote
import com.example.moviecatalogue.data.remote.response.tv.TvDetailResponse
import com.example.moviecatalogue.data.remote.response.tv.TvRemote

class DataRepository private constructor(private val remoteDataSource: RemoteDataSource): DataSource{
    companion object{
        @Volatile
        private var instance : DataRepository? = null
        fun getInstance (remoteDataSource: RemoteDataSource): DataRepository =
            instance ?: synchronized(this){
                instance?: DataRepository(remoteDataSource).apply { instance = this }
            }
    }


    override fun getMovie(): LiveData<List<MovieEntity>> {
        val getMovie = MutableLiveData<List<MovieEntity>>()
        remoteDataSource.getMovieList(object : RemoteDataSource.LoadMovieCallback{
            override fun onMovieReceive(movieResponse: List<MovieRemote>?) {
                val movieEntity = ArrayList<MovieEntity>()
                if (movieResponse != null){
                    for (moveires in movieResponse){
                        moveires.apply {
                            val movie = MovieEntity(id, title, date, image, desc)
                            movieEntity.add(movie)
                        }
                    }
                    getMovie.postValue(movieEntity)
                }
            }
        })
        return getMovie()
    }



    override fun getMovieDetail(movieId: String): LiveData<DetailEntity> {
        val movieDetailResult = MutableLiveData<DetailEntity>()
        remoteDataSource.getMovieDetail(object : RemoteDataSource.LoadMovieDetailCallback{
            override fun onDetailMovieReceive(detailMovieResponse: MovieDetailResponse?) {

                if (detailMovieResponse != null){
                    with(detailMovieResponse){
                        val listGenre = ArrayList<String>()
                        for (genre in genres){
                            listGenre.add(genre.name)
                        }
                        val detailEntity = DetailEntity(
                            id = id,
                            title = title,
                            date = date,
                            genres = listGenre,
                            imageDetail = imageDetail,
                            desc = desc
                        )
                        movieDetailResult.postValue(detailEntity)
                    }
                }
            }
        }, movieId)
        return movieDetailResult
    }

    override fun getTv(): LiveData<List<TvEntity>> {
        val getTv = MutableLiveData<List<TvEntity>>()
        remoteDataSource.getTvList(object : RemoteDataSource.LoadTvCallback{
            override fun onTvReceive(tvResponse: List<TvRemote>?) {
                val tvEntity = ArrayList<TvEntity>()
                if (tvResponse != null){
                    for (tvres in tvResponse){
                        tvres.apply {
                            val tv = TvEntity(
                                id,
                                title,
                                date,
                                image,
                                desc
                            )
                            tvEntity.add(tv)
                        }
                    }
                    getTv.postValue(tvEntity)
                }
            }

        })
        return getTv()
    }

    override fun getTvDetail(tvId: String): LiveData<DetailEntity> {
        val tvDetailResult = MutableLiveData<DetailEntity>()
        remoteDataSource.getTvDetail(object : RemoteDataSource.LoadTvDetailCallback{
            override fun onTvDetailReceive(tvDetailResponse: TvDetailResponse?) {
                if (tvDetailResponse != null){
                    with(tvDetailResponse){
                        val listGenre = ArrayList<String>()
                        for (genre in genres){
                            listGenre.add(genre.name)
                        }
                        val detailEntity = DetailEntity(
                            id = id,
                            title = title,
                            date = date,
                            genres = listGenre,
                            imageDetail = imageDetail,
                            desc = desc
                        )
                        tvDetailResult.postValue(detailEntity)
                    }
                }
            }
        }, tvId)
        return tvDetailResult
    }


}