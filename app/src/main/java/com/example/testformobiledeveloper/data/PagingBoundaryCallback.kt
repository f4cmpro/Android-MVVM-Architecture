package com.example.testformobiledeveloper.data

import androidx.paging.PagedList

class PagingBoundaryCallback<T: Any>(
    val onFirstLoad: () -> Unit,
    val onAddMore: (T) -> Unit
) : PagedList.BoundaryCallback<T>() {

    override fun onZeroItemsLoaded() {
        onFirstLoad()
    }

    override fun onItemAtEndLoaded(itemAtEnd: T) {
        onAddMore(itemAtEnd)
    }

    /**
     * Using for refresh data
     */
    fun refresh() {
        onZeroItemsLoaded()
    }

    /**
     * Using for retry load more in case if you want to retry load more after have a network error occurred
     */
    fun retryAddMore(itemAtEnd: T?) {
        itemAtEnd?.let {
            onItemAtEndLoaded(it)
        }
    }

}