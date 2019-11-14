package com.arivas.moviesappkotlin.di.module

import android.app.Application
import com.arivas.moviesappkotlin.application.BaseApp
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class ApplicationModule(private val baseApp: BaseApp) {

    @Provides
    @Singleton
    fun provideApplication(): Application {
        return baseApp
    }
}