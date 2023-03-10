package com.example.movieappazi.app.utils.recyclerview.animations.custom

import androidx.recyclerview.widget.RecyclerView

class SlideBackCommonAnimator : CommonItemAnimator {

    override fun preAnimateAdd(holder: RecyclerView.ViewHolder) {
        holder.itemView.translationY = -holder.itemView.height.toFloat() * 5
        holder.itemView.alpha = 0f
    }

    override fun animateAdd(holder: RecyclerView.ViewHolder) {
        holder.itemView.animate().translationY(0f)
        holder.itemView.animate().alpha(1f)
    }


    override fun animateRemove(holder: RecyclerView.ViewHolder) {
        holder.itemView.animate().translationY(holder.itemView.height.toFloat() * 8)
        holder.itemView.animate().duration = 1000
    }
}