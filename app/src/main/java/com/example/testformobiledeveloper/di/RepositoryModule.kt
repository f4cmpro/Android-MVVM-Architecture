package com.example.testformobiledeveloper.di

import com.example.testformobiledeveloper.data.local.dao.AssignmentDao
import com.example.testformobiledeveloper.data.local.dao.WorkoutDao
import com.example.testformobiledeveloper.data.remote.GetWorkoutListApiService
import com.example.testformobiledeveloper.data.repository.IWorkoutListRepository
import com.example.testformobiledeveloper.data.repository.WorkoutListRepository
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class RepositoryModule {
    @Singleton
    @Provides
    fun provideWorkoutRepository(getWorkoutListApiService: GetWorkoutListApiService, workoutDao: WorkoutDao,
    assignmentDao: AssignmentDao)
            : IWorkoutListRepository = WorkoutListRepository(getWorkoutListApiService, workoutDao, assignmentDao)
}