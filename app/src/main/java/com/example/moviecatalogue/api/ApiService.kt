package com.example.moviecatalogue.api

import com.example.moviecatalogue.api.Network.API_KEY
import com.example.moviecatalogue.data.remote.response.movie.MovieDetailResponse
import com.example.moviecatalogue.data.remote.response.movie.MovieResponse
import com.example.moviecatalogue.data.remote.response.tv.TvDetailResponse
import com.example.moviecatalogue.data.remote.response.tv.TvResponse
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface ApiService {
    companion object{
        const val API_KEY = "40d4e6eaaaeba0ef05890d8799ae9d44"
    }

    @GET("movie/popular?api_key=$API_KEY")
    fun getMovieList(
        @Query("page") page: Int
    ): Call<MovieResponse>

    @GET("movie/{movie_id}?api_key=$API_KEY")
    fun getDetailMovies(
        @Path("movie_id") id: String
    ): Call<MovieDetailResponse>

    @GET("tv/popular?api_key=$API_KEY")
    fun getListTVShows(
        @Query("page") page: Int
    ): Call<TvResponse>

    @GET("tv/{tv_id}?api_key=$API_KEY")
    fun getDetailTVShows(
        @Path("tv_id") id: String
    ): Call<TvDetailResponse>
}