package com.example.testformobiledeveloper.base

import android.view.View
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.LifecycleRegistry
import androidx.recyclerview.widget.RecyclerView

/**
 * LifecycleOwnerをもったViewHolder
 */
abstract class LifecycleViewHolder(itemView: View) : RecyclerView.ViewHolder(
    itemView), LifecycleOwner {

    private val lifecycleRegistry by lazy { LifecycleRegistry(this) }

    init {
        lifecycleRegistry.currentState = Lifecycle.State.INITIALIZED
    }

    fun onCreate() {
        lifecycleRegistry.currentState = Lifecycle.State.CREATED
        lifecycleRegistry.currentState = Lifecycle.State.STARTED
    }

    fun onDestroy() {
        lifecycleRegistry.currentState = Lifecycle.State.DESTROYED
    }

    override fun getLifecycle(): Lifecycle {
        return lifecycleRegistry
    }
}