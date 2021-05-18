package com.example.moviecatalogue.data.remote.dataSource


import android.util.Log
import com.example.moviecatalogue.api.ApiConfig
import com.example.moviecatalogue.api.Network.API_KEY
import com.example.moviecatalogue.data.remote.response.movie.MovieDetailResponse
import com.example.moviecatalogue.data.remote.response.movie.MovieRemote
import com.example.moviecatalogue.data.remote.response.movie.MovieResponse
import com.example.moviecatalogue.data.remote.response.tv.TvDetailResponse
import com.example.moviecatalogue.data.remote.response.tv.TvRemote
import com.example.moviecatalogue.data.remote.response.tv.TvResponse
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response


class RemoteDataSource{
    companion object{
        const val TAG = "remoteDataSource"

        @Volatile
        private var instance: RemoteDataSource? = null
        fun getInstance(): RemoteDataSource = instance ?: synchronized(this) {
            RemoteDataSource().apply { instance = this }
        }
    }

    fun getMovieList(callback : LoadMovieCallback){
        val client = ApiConfig.getApiService().getMovieList(1)
            client.enqueue(object : Callback<MovieResponse> {
            override fun onResponse(
                call: Call<MovieResponse>,
                response: Response<MovieResponse>
            ) {
                callback.onMovieReceive(response.body()?.result)
            }
            override fun onFailure(call: Call<MovieResponse>, throwable: Throwable) {
                Log.e(TAG, "Failure ${throwable .message}")
            }

        })
    }

    fun getMovieDetail(callback : LoadMovieDetailCallback, id : String){
        val client = ApiConfig.getApiService().getDetailMovies(id)
            client.enqueue(object : Callback<MovieDetailResponse> {
                override fun onResponse(
                    call: Call<MovieDetailResponse>,
                    response: Response<MovieDetailResponse>
                ) {
                    callback.onDetailMovieReceive(response.body())
                }

                override fun onFailure(call: Call<MovieDetailResponse>, throwable: Throwable) {
                    Log.e(TAG, "Failure ${throwable .message}")
                }

            })
    }

    fun getTvList(callback : LoadTvCallback){
        val client = ApiConfig.getApiService().getListTVShows(1)
        client.enqueue(object : Callback<TvResponse> {
            override fun onResponse(call: Call<TvResponse>, response: Response<TvResponse>) {
                callback.onTvReceive(response.body()?.result)
            }
            override fun onFailure(call: Call<TvResponse>, t: Throwable) {
                Log.e(TAG, "Failure ${t .message}")
            }
        })
    }

    fun getTvDetail(callback : LoadTvDetailCallback, id : String){
        val client = ApiConfig.getApiService().getDetailTVShows(id)
        client.enqueue(object : Callback<TvDetailResponse> {
            override fun onResponse(call: Call<TvDetailResponse>, response: Response<TvDetailResponse>
            ) {
                callback.onTvDetailReceive(response.body())
            }

            override fun onFailure(call: Call<TvDetailResponse>, t: Throwable) {
                Log.e(TAG, "Failure ${t .message}")
            }

        })
    }


    interface LoadMovieCallback{
        fun onMovieReceive(movieResponse: List<MovieRemote>?)
    }

    interface LoadMovieDetailCallback{
        fun onDetailMovieReceive(detailMovieResponse: MovieDetailResponse?)
    }

    interface LoadTvCallback{
        fun onTvReceive(tvResponse: List<TvRemote>?)
    }

    interface LoadTvDetailCallback{
        fun onTvDetailReceive(tvDetailResponse: TvDetailResponse?)
    }

}