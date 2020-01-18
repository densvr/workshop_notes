package com.danser.workshop4_login

import android.app.Application
import com.danser.workshop4_login.di.component.ApplicationComponent
import com.danser.workshop4_login.di.component.DaggerApplicationComponent
import com.danser.workshop4_login.di.component.IApplicationComponentProvider
import com.danser.workshop4_login.di.module.ApplicationModule

class NotesApplication : Application(), IApplicationComponentProvider {

    private lateinit var appComponent: ApplicationComponent

    override fun get(): ApplicationComponent = appComponent

    override fun onCreate() {
        super.onCreate()
        appComponent = DaggerApplicationComponent
            .builder()
            .applicationModule(ApplicationModule(applicationContext))
            .build()
    }
}
