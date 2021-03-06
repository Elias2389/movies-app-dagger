package com.arivas.moviesappkotlin.application

import android.app.Application
import com.arivas.moviesappkotlin.di.component.ApplicationComponent
import com.arivas.moviesappkotlin.di.component.DaggerApplicationComponent
import com.arivas.moviesappkotlin.di.module.ApplicationModule

class BaseApp: Application() {

    private lateinit var component: ApplicationComponent

    override fun onCreate() {
        super.onCreate()

        component = DaggerApplicationComponent
            .builder()
            .applicationModule(ApplicationModule(this))
            .build()
    }

    fun getComponent(): DaggerApplicationComponent {
        return component as DaggerApplicationComponent
    }
}