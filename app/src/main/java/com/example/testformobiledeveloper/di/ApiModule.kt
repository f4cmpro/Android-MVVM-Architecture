package com.example.testformobiledeveloper.di

import com.example.testformobiledeveloper.data.remote.GetWorkoutListApiService
import com.example.testformobiledeveloper.data.retrofit.ApiManager
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class ApiModule {
    @Singleton
    @Provides
    fun provideGetTimelineListApiService(apiManager: ApiManager): GetWorkoutListApiService = apiManager.create(GetWorkoutListApiService::class.java)
}