package com.example.testformobiledeveloper.di

import com.example.testformobiledeveloper.MainApplication
import dagger.Component
import dagger.android.AndroidInjectionModule
import dagger.android.AndroidInjector
import javax.inject.Singleton

@Singleton
@Component(modules = [
    AndroidInjectionModule::class,
    ApiModule::class,
    ViewModelModule::class,
    RoomModule::class,
    RepositoryModule::class,
    ActivityBindingModule::class,
])
interface AppComponent : AndroidInjector<MainApplication> {

    @Component.Factory
    abstract class Factory : AndroidInjector.Factory<MainApplication>
}