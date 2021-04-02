package com.example.testformobiledeveloper.data.remote

import com.example.testformobiledeveloper.data.model.WorkoutData
import com.example.testformobiledeveloper.data.retrofit.ResponseBase
import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
class GetWorkoutListResponse(
    @Json(name = "data") val workouts: List<WorkoutData>
) : ResponseBase()