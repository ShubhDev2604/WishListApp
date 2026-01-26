package com.lifehive.app

import android.app.Application
import com.lifehive.app.singletons.Graph
import dagger.hilt.android.HiltAndroidApp

@HiltAndroidApp
class LifeHive:Application() {
    override fun onCreate() {
        super.onCreate()
        Graph.provide(this)
    }
}