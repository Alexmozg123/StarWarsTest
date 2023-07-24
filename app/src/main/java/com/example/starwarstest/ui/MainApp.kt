package com.example.starwarstest.ui

import android.app.Application
import com.example.starwarstest.data.api.ApiService
import com.example.starwarstest.data.room.FavouriteDatabase
import com.example.starwarstest.di.AppComponent
import com.example.starwarstest.di.DaggerAppComponent
import javax.inject.Inject

class MainApp : Application() {

    lateinit var appComponent: AppComponent

    @Inject
    lateinit var favouriteDatabase: FavouriteDatabase
    @Inject
    lateinit var apiService: ApiService

    override fun onCreate() {
        super.onCreate()
        appComponent = DaggerAppComponent.builder()
            .bindContext(this)
            .build()
        appComponent.inject(this)
    }
}

