package com.example.sample.di

import android.app.Application
import com.example.sample.di.component.DaggerBasicComponent
import com.example.sample.di.module.ContextModule


/**
 * Created by neeraj on 30/04/2018.
 */

class BaseApp : Application() {

    private lateinit var basicComponent: DaggerBasicComponent.Builder

    override fun onCreate() {
        super.onCreate()
        basicComponent = DaggerBasicComponent.builder()
                .contextModule(ContextModule(applicationContext))
    }

    fun getInjection() = basicComponent
}
