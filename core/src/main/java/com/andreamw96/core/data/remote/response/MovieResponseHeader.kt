package com.andreamw96.core.data.remote.response

import com.google.gson.annotations.SerializedName

data class MovieResponseHeader (
    @SerializedName("page")
    var page: Int = 0,
    @SerializedName("results")
    var results: List<MovieResponse> = emptyList(),
    @SerializedName("total_pages")
    var totalPages: Int = 0,
    @SerializedName("total_results")
    var totalResults: Int = 0
)