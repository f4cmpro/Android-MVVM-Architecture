package com.example.testformobiledeveloper.di

import com.example.testformobiledeveloper.ui.WorkoutActivity
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
interface ActivityBindingModule {

    @ContributesAndroidInjector
    fun contributeWorkoutActivityInjector(): WorkoutActivity

}