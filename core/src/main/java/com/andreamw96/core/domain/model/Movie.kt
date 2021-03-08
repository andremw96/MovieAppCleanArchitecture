package com.andreamw96.core.domain.model

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize

@Parcelize
data class Movie(
    val id: Int,
    val backdropPath: String,
    val overview: String,
    val releaseDate: String,
    val title: String,
    val voteAverage: Double
) : Parcelable