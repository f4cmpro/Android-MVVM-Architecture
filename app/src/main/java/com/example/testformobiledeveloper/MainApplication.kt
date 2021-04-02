package com.example.testformobiledeveloper

import android.content.Context
import com.example.testformobiledeveloper.di.DaggerAppComponent
import dagger.android.AndroidInjector
import dagger.android.DaggerApplication

open class MainApplication : DaggerApplication() {

    companion object {
        lateinit var instance: MainApplication private set
        val context: Context get() = instance.applicationContext
    }

    override fun applicationInjector(): AndroidInjector<out DaggerApplication> = DaggerAppComponent.factory().create(this)

    override fun onCreate() {
        super.onCreate()
        instance = this
    }
}