package com.example.moviecatalogue.data.remote.response.movie

import com.google.gson.annotations.SerializedName

data class MovieResponse(
    @SerializedName("results")
    val result: List<MovieRemote>
)