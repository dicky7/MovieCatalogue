package com.example.moviecatalogue.data.remote.response.tv

import com.google.gson.annotations.SerializedName

data class TvResponse(
    @SerializedName("results")
    val result: List<TvRemote>
)