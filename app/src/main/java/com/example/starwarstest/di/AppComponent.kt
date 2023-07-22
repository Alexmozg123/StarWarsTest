package com.example.starwarstest.di

import android.content.Context
import com.example.starwarstest.ui.screens.favourites.FavouriteFragment
import com.example.starwarstest.ui.screens.searchscreens.SearchFragment
import dagger.BindsInstance
import dagger.Component

@Component(modules = [BindsModule::class, NetworkModule::class, DatabaseModule::class])
interface AppComponent {

    fun inject(searchFragment: SearchFragment): SearchFragment
    fun inject(favouriteFragment: FavouriteFragment): FavouriteFragment

    @Component.Builder
    interface Builder {

        @BindsInstance
        fun bindContext(context: Context): Builder

        fun build(): AppComponent
    }
}