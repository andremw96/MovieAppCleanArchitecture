package com.andreamw96.core.data.remote.response

import com.google.gson.annotations.SerializedName

data class MovieResponse (
    @SerializedName("backdrop_path")
    var backdropPath: String,
    @SerializedName("id")
    var id: Int = -1,
    @SerializedName("overview")
    var overview: String,
    @SerializedName("release_date")
    var releaseDate: String,
    @SerializedName("title")
    var title: String,
    @SerializedName("vote_average")
    var voteAverage: Double = 0.0
)