package com.example.testformobiledeveloper.data.remote

import kotlinx.coroutines.Deferred
import retrofit2.http.GET

interface GetWorkoutListApiService {

    @GET("workouts")
    fun getWorkoutListAsync(): Deferred<GetWorkoutListResponse>

}