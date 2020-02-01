package com.danser.workshop4_login.di

import android.content.Context
import com.danser.workshop4_login.NotesApplication
import com.danser.workshop4_login.di.module.ApplicationModule
import com.danser.workshop4_login.di.module.NotesFeedModule

class Injector(context: Context)  {

    val applicationModule: ApplicationModule = ApplicationModule(context)
    val notesFeedModule: NotesFeedModule by lazy { NotesFeedModule(applicationModule) }
}


val injector: Injector get() = NotesApplication.injector
