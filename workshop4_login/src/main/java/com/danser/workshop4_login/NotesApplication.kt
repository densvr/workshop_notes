package com.danser.workshop4_login

import android.app.Application
import com.danser.workshop4_login.di.Injector

class NotesApplication : Application() {

    override fun onCreate() {
        super.onCreate()

        injector = Injector(applicationContext)
    }


    companion object {
        lateinit var injector: Injector
    }
}

