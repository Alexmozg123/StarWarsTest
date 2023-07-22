package com.example.starwarstest.ui

import android.app.Application
import com.example.starwarstest.di.AppComponent
import com.example.starwarstest.di.DaggerAppComponent

class MainApp : Application() {

    lateinit var appComponent: AppComponent

    override fun onCreate() {
        super.onCreate()
        appComponent = DaggerAppComponent.builder()
            .bindContext(this)
            .build()
    }
}

