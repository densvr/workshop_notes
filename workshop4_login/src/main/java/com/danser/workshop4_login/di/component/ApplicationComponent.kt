package com.danser.workshop4_login.di.component

import com.danser.workshop4_login.di.module.ApplicationModule
import dagger.Component

// Definition of the Application graph
@Component(modules = [ApplicationModule::class])
interface ApplicationComponent {
    val notesFeed: NotesFeedComponent.Factory
}

interface IApplicationComponentProvider {
    fun get(): ApplicationComponent
}
