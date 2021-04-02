package com.example.testformobiledeveloper.base

import androidx.paging.PagedListAdapter
import androidx.recyclerview.widget.DiffUtil

abstract class LifecyclePagedListAdapter<T: Any, VH : LifecycleViewHolder>(
    diffCallback: DiffUtil.ItemCallback<T>
) : PagedListAdapter<T, VH>(diffCallback) {

    override fun onViewAttachedToWindow(holder: VH) {
        super.onViewAttachedToWindow(holder)
        holder.onCreate()
    }

    override fun onViewDetachedFromWindow(holder: VH) {
        super.onViewDetachedFromWindow(holder)
        holder.onDestroy()
    }

}