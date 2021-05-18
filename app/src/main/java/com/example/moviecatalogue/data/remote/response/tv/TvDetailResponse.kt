package com.example.moviecatalogue.data.remote.response.tv

import com.example.moviecatalogue.data.remote.response.GenreResponse
import com.google.gson.annotations.SerializedName

data class TvDetailResponse(
    @SerializedName("id")
    val id: Int,
    @SerializedName("original_name")
    val title: String,
    @SerializedName("first_air_date")
    val date: String,
    @SerializedName("genres")
    val genres: List<GenreResponse>,
    @SerializedName("poster_path")
    val imageDetail: String,
    @SerializedName("overview")
    val desc: String
)