package com.lifehive.app

import android.app.Application
import com.lifehive.app.Graph

class LifeHive:Application() {
    override fun onCreate() {
        super.onCreate()
        Graph.provide(this)
    }
}