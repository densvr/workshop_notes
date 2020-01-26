package com.danser.workshop4_login

import android.app.Application
import com.danser.workshop4_login.di.module.appModule
import com.danser.workshop4_login.di.module.notesFeedModule
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidFileProperties
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin

class NotesApplication : Application() {

    override fun onCreate() {
        super.onCreate()

        startKoin {
            // use AndroidLogger as Koin Logger - default Level.INFO
            androidLogger()
            // use the Android context given there
            androidContext(this@NotesApplication)
            // load properties from assets/koin.properties file
            androidFileProperties()
            // module list
            modules(listOf(appModule, notesFeedModule))
        }
    }
}

