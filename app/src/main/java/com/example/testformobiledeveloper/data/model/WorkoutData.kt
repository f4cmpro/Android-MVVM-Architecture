package com.example.testformobiledeveloper.data.model

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
@Suppress("PropertyName")
data class WorkoutData(
    @Json(name = "_id") val id : String,
    val day : Int,
    val assignments : List<AssignmentData>,
    val page : Int = 1
)