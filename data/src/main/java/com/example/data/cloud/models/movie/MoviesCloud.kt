package com.example.data.cloud.models.movie

import com.google.gson.annotations.SerializedName

data class MoviesCloud(
    @SerializedName("page") val page: Int,
    @SerializedName("results") val movies: List<MovieCloud>,
    @SerializedName("total_pages") val totalPage: Int,
    @SerializedName("total_results") val totalResults: Int,// 123
)