package com.example.movieappazi.app.models.person

import android.os.Parcelable
import com.example.movieappazi.app.models.movie.MovieUi
import kotlinx.android.parcel.Parcelize

@Parcelize
class PersonUi(
    val profile_path: String?,
    val adult: Boolean,
    val id: Int,
    val known_for: List<MovieUi>,
    val name: String,
    val popularity: Double
) : Parcelable