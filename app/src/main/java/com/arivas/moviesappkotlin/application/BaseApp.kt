package com.arivas.moviesappkotlin.application

import android.app.Application
import com.arivas.moviesappkotlin.di.component.ApplicationComponent
import com.arivas.moviesappkotlin.di.component.DaggerApplicationComponent
import com.facebook.drawee.backends.pipeline.Fresco

class BaseApp: Application() {

    lateinit var component: ApplicationComponent

    override fun onCreate() {
        super.onCreate()

        //Initialize Fresco
        Fresco.initialize(this)

        component = DaggerApplicationComponent
            .builder()
            .build()
    }

    fun getComponent(): DaggerApplicationComponent {
        return component as DaggerApplicationComponent
    }
}