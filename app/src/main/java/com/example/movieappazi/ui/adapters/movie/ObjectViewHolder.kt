package com.example.movieappazi.ui.adapters.movie

import android.view.View
import android.widget.ImageView
import android.widget.TextView
import androidx.cardview.widget.CardView
import androidx.recyclerview.widget.RecyclerView
import com.example.data.cloud.api.utils.Utils
import com.example.movieappazi.R
import com.example.movieappazi.app.models.movie.CastUi
import com.example.movieappazi.app.models.movie.MovieUi
import com.example.movieappazi.app.models.movie.tv_shows.SeriesUi
import com.example.movieappazi.app.models.person.PersonUi
import com.squareup.picasso.Picasso
import com.vaibhavlakhera.circularprogressview.CircularProgressView

class ObjectViewHolder(val view: View) : RecyclerView.ViewHolder(view) {
    private val image = view.findViewById<ImageView>(R.id.posterImage)
    private val movie_rating = view.findViewById<TextView>(R.id.movie_rating_txt)
    private val progressView = view.findViewById<CircularProgressView>(R.id.progressView)
    private val castName = view.findViewById<TextView>(R.id.titleText)
    val itemMovie = view.findViewById<CardView>(R.id.motion_layout)

    fun bindMovie(movie: MovieUi) {
        bind(movie.posterPath, movie.rating)
        movie_rating.text = movie.rating.toFloat().toString()
    }

    fun bindPerson(person: PersonUi) {
        bind(posterPath = person.profile_path, popularity =person.popularity)
    }

    fun bindTvMovie(tv: SeriesUi) {
        bind(tv.posterPath, tv.voteAverage)
    }

    fun bindActors(person: CastUi) {
        bind(person.profilePath, person.popularity)
        binCastTitle(name = person.name)
    }

    private fun bind(posterPath: String?, popularity: Double) {
        Picasso.get().load(Utils.POSTER_PATH_URL + posterPath).into(image)
        val voteAverage = (popularity * 10.0)
        if (voteAverage.toInt() >= 70) {
            progressView.setProgressColorRes(R.color.green)
        } else if (voteAverage.toInt() in 51..69) {
            progressView.setProgressColorRes(android.R.color.holo_orange_light)
        } else {
            progressView.setProgressColorRes(android.R.color.holo_red_dark)
        }
        progressView.setProgress(voteAverage.toInt(), true)
    }

    private fun binCastTitle(name: String) {
        castName.text = name
    }
}