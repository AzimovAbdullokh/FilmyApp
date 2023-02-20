package com.example.movieappazi.ui.adapters.movie

import android.view.LayoutInflater
import android.view.ViewGroup
import android.view.animation.AnimationUtils
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import com.example.movieappazi.R
import com.example.movieappazi.ui.adapters.movie.diffcallbacks.MovieItemDiffCallback
import com.example.movieappazi.ui.adapters.movie.listener.RvClickListener
import com.example.movieappazi.app.models.movie.MovieUi
import com.example.movieappazi.app.utils.extensions.downEffect
import com.example.movieappazi.app.utils.extensions.startSlideInLeftAnim
import com.example.movieappazi.app.utils.extensions.startSlideInRightAnim


class MovieItemAdapter(
    private val objectViewType: Int,
    private val listener: RvClickListener<MovieUi>,
) : ListAdapter<MovieUi, ObjectViewHolder>(MovieItemDiffCallback()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ObjectViewHolder {
        val layout = when (viewType) {
            PORTRAIT_TYPE -> R.layout.object_item_portrait
            HORIZONTAL_TYPE -> R.layout.item_fav_movies
            POPULAR_TYPE -> R.layout.item_popular_movies
            SEEMORETYPE -> R.layout.item_for_seemore
            FANCY_TYPE -> R.layout.item_for_fancy_movie
            else -> throw RuntimeException("Unknown view type: $viewType")
        }
        val view = LayoutInflater.from(parent.context).inflate(layout, parent, false)
        return ObjectViewHolder(view)
    }

    override fun onBindViewHolder(holder: ObjectViewHolder, position: Int) {
        holder.view.setOnLongClickListener {
            listener.onItemClick(getItem(position))
            true
        }
        holder.view.downEffect().setOnClickListener {
            listener.onLongClick(getItem(position))
        }
        holder.bindMovie(getItem(position))

        holder.itemMovie.startSlideInLeftAnim()
    }


    override fun getItemViewType(position: Int): Int {
        return if (objectViewType == HORIZONTAL_TYPE) {
            HORIZONTAL_TYPE
        } else if (objectViewType == PORTRAIT_TYPE) {
            PORTRAIT_TYPE
        } else if (objectViewType == SEEMORETYPE) {
            SEEMORETYPE
        } else if (objectViewType == FANCY_TYPE) {
            FANCY_TYPE
        } else {
            POPULAR_TYPE
        }
    }

    companion object {
        const val PORTRAIT_TYPE = 0
        const val HORIZONTAL_TYPE = 1
        const val POPULAR_TYPE = 2
        const val SEEMORETYPE = 3
        const val FANCY_TYPE = 4
    }
}

