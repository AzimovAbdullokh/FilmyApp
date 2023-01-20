package com.example.movieappazi.ui.zAdapter.movie.adapter_for_pubat

import android.view.LayoutInflater
import android.view.ViewGroup
import android.view.animation.AnimationUtils
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import com.example.movieappazi.R
import com.example.movieappazi.ui.zAdapter.movie.adapter_for_popular.MovieItemAdapter
import com.example.movieappazi.ui.zAdapter.movie.diffcallbacks.MovieDiffCallBack
import com.example.movieappazi.ui.zAdapter.movie.diffcallbacks.MovieItemDiffCallback
import com.example.movieappazi.ui.zAdapter.movie.listener_for_adapters.RvClickListener
import com.example.movieappazi.ui.zAdapter.movie.view_holders.ObjectViewHolder
import com.example.movieappazi.uiModels.movie.MovieUi

class PublishedAtMoviesAdapter(
    private val objectViewType: Int,
    private val listener: RvClickListener<MovieUi>,
) : ListAdapter<MovieUi, ObjectViewHolder>(MovieItemDiffCallback()) {

    var publishedMoviesList = listOf<MovieUi>()
        set(value) {
            val callback = MovieDiffCallBack(value, publishedMoviesList)
            val diffResult = DiffUtil.calculateDiff(callback)
            diffResult.dispatchUpdatesTo(this)
            field = value
        }

    override fun getItemViewType(position: Int): Int {
        return if (objectViewType == MovieItemAdapter.PORTRAIT_TYPE) {
            MovieItemAdapter.PORTRAIT_TYPE
        } else MovieItemAdapter.HORIZONTAL_TYPE
    }

    companion object {
        const val PORTRAIT_TYPE = 1
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ObjectViewHolder {
        val layout = when (viewType) {
            MovieItemAdapter.PORTRAIT_TYPE -> R.layout.object_item_portrait
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
        holder.view.setOnClickListener {
            listener.onLongClick(getItem(position))
        }
        holder.bindMovie(getItem(position))

        holder.itemMovie.startAnimation(AnimationUtils.loadAnimation(holder.itemView.context,
            R.anim.anim_for_recyclerview))
    }

}