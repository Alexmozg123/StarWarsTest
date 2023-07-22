package com.example.starwarstest.di

import com.example.starwarstest.data.repository.RepositoryImpl
import com.example.starwarstest.domain.repositories.RetrofitRepository
import com.example.starwarstest.domain.repositories.RoomRepository
import dagger.Binds
import dagger.Module

@Module
interface BindsModule {

    @Suppress("FunctionName")
    @Binds
    fun bindsRepositoryImpl_to_RoomRepository(
        repositoryImpl: RepositoryImpl
    ): RoomRepository

    @Suppress("FunctionName")
    @Binds
    fun bindsRepositoryImpl_to_RetrofitRepository(
        repositoryImpl: RepositoryImpl
    ): RetrofitRepository
}