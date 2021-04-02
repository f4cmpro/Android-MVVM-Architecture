package com.example.testformobiledeveloper.util

import android.view.View
import android.widget.TextView
import androidx.core.content.ContextCompat
import androidx.databinding.BindingAdapter
import com.example.testformobiledeveloper.R
import com.example.testformobiledeveloper.ui.WorkoutViewModel
import com.example.testformobiledeveloper.ui.WorkoutViewModel.AssignmentStatus

@BindingAdapter("android:status")
fun TextView.setColorAndText(status: Int) {
    text = when (status) {
        AssignmentStatus.MISSED.id -> {
            visibility = View.VISIBLE
            setTextColor(ContextCompat.getColor(context, R.color.color_FF5E5E))
            AssignmentStatus.MISSED.status
        }
        AssignmentStatus.COMPLETED.id -> {
            visibility = View.VISIBLE
            setTextColor(ContextCompat.getColor(context, R.color.white))
            AssignmentStatus.COMPLETED.status
        }
        else -> {
            visibility = View.GONE
            ""
        }
    }
}

@BindingAdapter("android:status", "android:totalExercises")
fun TextView.setExercises(status: Int, totalExercises: Int) {
    text = when (status) {
        AssignmentStatus.COMPLETED.id -> {
            ""
        }
        AssignmentStatus.MISSED.id -> {
            " \u2022 " + resources.getString(R.string.total_exercises, totalExercises)
        }
        else -> {
            resources.getString(R.string.total_exercises, totalExercises)
        }
    }
}

@BindingAdapter("android:titleColor")
fun TextView.setTitleColor(status: Int) {
    when (status) {
        AssignmentStatus.COMPLETED.id -> {
            setTextColor(ContextCompat.getColor(context, R.color.white))
        }

        else -> {
            setTextColor(ContextCompat.getColor(context, R.color.color_1E0A3C))
        }
    }
}

@BindingAdapter("android:dayColor")
fun TextView.setDayColor(dayOfWeek: WorkoutViewModel.DayOfWeek) {
    if (dayOfWeek.id == DateUtils.getCurrentDayOfWeek()) {
        setTextColor(ContextCompat.getColor(context, R.color.color_7470EF))
    } else {
        setTextColor(ContextCompat.getColor(context, R.color.color_7B7E91))
    }
}

