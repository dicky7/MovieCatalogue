package com.example.moviecatalogue.data.remote.response.movie

import com.example.moviecatalogue.data.remote.response.GenreResponse
import com.google.gson.annotations.SerializedName

data class MovieDetailResponse(
    @SerializedName("id")
    val id: Int,
    @SerializedName("title")
    val title: String,
    @SerializedName("release_date")
    val date: String,
    @SerializedName("genres")
    val genres: List<GenreResponse>,
    @SerializedName("poster_path")
    val imageDetail: String,
    @SerializedName("overview")
    val desc: String

)