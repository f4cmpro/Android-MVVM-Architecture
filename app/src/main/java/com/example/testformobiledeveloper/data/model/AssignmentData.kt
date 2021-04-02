package com.example.testformobiledeveloper.data.model

import com.squareup.moshi.Json

data class AssignmentData(
    @Json(name = "_id") val id: String,
    val title: String,
    val status: Int,
    @Json(name = "total_exercise") val totalExercise: Int,
    var isChecked: Boolean = false
)