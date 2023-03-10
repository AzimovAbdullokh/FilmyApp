package com.example.movieappazi.app.utils.recyclerview

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.annotation.LayoutRes
import androidx.recyclerview.widget.DiffUtil
import androidx.viewbinding.ViewBinding
import com.example.movieappazi.app.base.BaseViewHolder

interface ItemFingerprint<V : ViewBinding, I : Item> {

    fun isRelativeItem(item: Item): Boolean

    @LayoutRes
    fun getLayoutId(): Int

    fun getViewHolder(
        layoutInflater: LayoutInflater,
        parent: ViewGroup,
    ): BaseViewHolder<V, I>

    fun getDiffUtil(): DiffUtil.ItemCallback<I>
}