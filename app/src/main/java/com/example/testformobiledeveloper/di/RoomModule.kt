package com.example.testformobiledeveloper.di

import androidx.room.Room
import com.example.testformobiledeveloper.MainApplication
import com.example.testformobiledeveloper.data.local.WorkoutDatabase
import com.example.testformobiledeveloper.data.local.dao.AssignmentDao
import com.example.testformobiledeveloper.data.local.dao.WorkoutDao
import dagger.Module
import dagger.Provides
import javax.inject.Singleton


@Module
class RoomModule {

    @Singleton
    @Provides
    fun provideDb() = Room.databaseBuilder(MainApplication.context, WorkoutDatabase::class.java, "Workout.db")
        .fallbackToDestructiveMigration()
        .build()

    @Singleton
    @Provides
    fun provideWorkoutDao(workoutDatabase: WorkoutDatabase): WorkoutDao = workoutDatabase.workoutDao()

    @Singleton
    @Provides
    fun provideAssignmentDao(workoutDatabase: WorkoutDatabase): AssignmentDao = workoutDatabase.assignmentDao()
}