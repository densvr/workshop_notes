package com.danser.workshop4_login.di.component

import com.danser.workshop4_login.LoginActivity
import com.danser.workshop4_login.NotesFeedActivity
import com.danser.workshop4_login.di.module.ApplicationModule
import com.danser.workshop4_login.di.module.NotesFeedModule
import dagger.Component

// Definition of the Application graph
@Component(modules = [ApplicationModule::class, NotesFeedModule::class])
interface ApplicationComponent {
    fun inject(activity: NotesFeedActivity)
    fun inject(activity: LoginActivity)
}

interface IApplicationComponentProvider {
    fun get(): ApplicationComponent
}
