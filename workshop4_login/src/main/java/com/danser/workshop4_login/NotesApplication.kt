package com.danser.workshop4_login

import android.app.Application
import com.danser.workshop4_login.data.INotesRepository
import com.danser.workshop4_login.data.db.INotesDatabaseProvider
import com.danser.workshop4_login.data.db.NotesDatabase
import com.danser.workshop4_login.data.db.NotesDatabaseProvider
import com.danser.workshop4_login.di.NoteFeedPresentationDependencies
import com.danser.workshop4_login.di.NotesFeedPresentationFactory
import com.danser.workshop4_login.di.component.ApplicationComponent
import com.danser.workshop4_login.di.component.DaggerApplicationComponent
import com.danser.workshop4_login.di.component.IApplicationComponentProvider
import com.danser.workshop4_login.di.module.ApplicationModule
import com.danser.workshop4_login.di.module.appModule
import com.danser.workshop4_login.di.module.notesFeedModule
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidFileProperties
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin
import org.koin.core.qualifier.named
import org.koin.dsl.module

class NotesApplication : Application(), IApplicationComponentProvider {

    private lateinit var appComponent: ApplicationComponent

    override fun get(): ApplicationComponent = appComponent

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

        appComponent = DaggerApplicationComponent
            .builder()
            .applicationModule(ApplicationModule(applicationContext))
            .build()
    }
}

