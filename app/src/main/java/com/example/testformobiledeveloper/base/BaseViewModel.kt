package com.example.testformobiledeveloper.base

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

abstract class BaseViewModel : ViewModel() {
    val loadingState = MutableLiveData<LoadState>(LoadState.None)
    val refreshState = MutableLiveData<RefreshState>(RefreshState.None)

    sealed class LoadState {
        object None : LoadState()

        object Loading : LoadState()
        object Loaded : LoadState()
        object Empty : LoadState()
        object Error : LoadState()

        fun isLoading(): Boolean = this is Loading
    }

    sealed class RefreshState {
        object None : RefreshState()

        object Refreshing : RefreshState()
        object Refreshed : RefreshState()
        object Empty : RefreshState()
        object Error : RefreshState()

        fun isRefreshing(): Boolean = this is Refreshing
    }
}
