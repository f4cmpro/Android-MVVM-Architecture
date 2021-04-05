package com.example.testformobiledeveloper.ui

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.example.testformobiledeveloper.base.BaseViewModel
import com.example.testformobiledeveloper.data.model.AssignmentData
import com.example.testformobiledeveloper.data.repository.WorkoutListRepository
import com.example.testformobiledeveloper.data.retrofit.TaskResult
import com.example.testformobiledeveloper.util.DateUtils
import kotlinx.coroutines.launch
import javax.inject.Inject

class WorkoutViewModel @Inject constructor(private val repository: WorkoutListRepository) :
    BaseViewModel() {

    val assignments0 = MutableLiveData<List<AssignmentData>>()
    val assignments1 = MutableLiveData<List<AssignmentData>>()
    val assignments2 = MutableLiveData<List<AssignmentData>>()
    val assignments3 = MutableLiveData<List<AssignmentData>>()
    val assignments4 = MutableLiveData<List<AssignmentData>>()
    val assignments5 = MutableLiveData<List<AssignmentData>>()
    val assignments6 = MutableLiveData<List<AssignmentData>>()

    val clickItem = MutableLiveData<Any>()

    private var isFirstLoading = false

    private var isRefreshing = false

    fun refresh() {
        if (isRefreshing) {
            return
        }
        isRefreshing = true
        refreshState.postValue(RefreshState.Refreshing)
        viewModelScope.launch {
            repository.clearCache()
        }
        fetchData()
    }

    fun firstLoad() {
        if (isFirstLoading)
            return

        isFirstLoading = true
        loadingState.postValue(LoadState.Loading)
        fetchData()
    }

    private fun fetchData() {
        viewModelScope.launch {
            val page = 1
            when (val response = repository.getWorkoutList(page = page, isRefresh = true)) {
                is TaskResult.Success -> {
                    val dataList = response.value
                    assignments0.postValue(dataList[0].assignments)
                    assignments1.postValue(dataList[1].assignments)
                    assignments2.postValue(dataList[2].assignments)
                    assignments3.postValue(dataList[3].assignments)
                    assignments4.postValue(dataList[4].assignments)
                    assignments5.postValue(dataList[5].assignments)
                    assignments6.postValue(dataList[6].assignments)
                    if (isFirstLoading) {
                        loadingState.postValue(if (dataList.isNotEmpty()) LoadState.Loaded else LoadState.Empty)
                        isFirstLoading = false
                    } else if (isRefreshing) {
                        refreshState.postValue(if (dataList.isNotEmpty()) RefreshState.Refreshed else RefreshState.Empty)
                        isRefreshing = false
                    }
                }

                is TaskResult.Failure -> {
                    if (isFirstLoading) {
                        loadingState.postValue(LoadState.Error)
                        isFirstLoading = false
                    } else if (isRefreshing) {
                        refreshState.postValue(RefreshState.Error)
                        isRefreshing = false
                    }
                }
            }
        }
    }

    fun getDayOfWeek(dayOfWeek: DayOfWeek): String {
        return DateUtils.getDayOfMonth(dayOfWeek.id)
    }

    enum class DayOfWeek(val id: Int, val day: String) {
        MONDAY(
            2,
            "MON"
        ),

        TUESDAY(
            3,
            "TUE"
        ),

        WEDNESDAY(
            4,
            "WED"
        ),

        THURSDAY(
            5,
            "THU"
        ),

        FRIDAY(
            6,
            "FRI"
        ),

        SATURDAY(
            7,
            "SAT"
        ),

        SUNDAY(
            8,
            "SUN"
        );
    }

    enum class AssignmentStatus(val id: Int, val status: String) {
        ASSIGNED(
            1,
            "Assigned"
        ),

        MISSED(
            0,
            "Missed"
        ),

        COMPLETED(
            2,
            "Completed"
        );

        companion object {
            private val map = values().associateBy(AssignmentStatus::id)
            public fun fromItem(itemId: Int) = map[itemId]
        }
    }
}