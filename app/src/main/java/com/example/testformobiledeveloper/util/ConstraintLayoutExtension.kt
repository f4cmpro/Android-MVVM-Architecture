package com.example.testformobiledeveloper.util

import android.view.View
import android.widget.TextView
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.core.content.ContextCompat
import androidx.databinding.BindingAdapter
import com.example.testformobiledeveloper.R
import com.example.testformobiledeveloper.ui.WorkoutViewModel.AssignmentStatus

@BindingAdapter("android:backgroundStatus")
fun ConstraintLayout.backgroundStatus(status: Int) {
    background = when (status) {
        AssignmentStatus.COMPLETED.id -> {
            ContextCompat.getDrawable(context, R.drawable.bg_assigment_completed)
        }
        else -> {
            ContextCompat.getDrawable(context, R.drawable.bg_assignment)
        }
    }
}