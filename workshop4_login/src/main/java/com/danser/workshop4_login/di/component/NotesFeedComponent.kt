package com.danser.workshop4_login.di.component

import com.danser.workshop4_login.NotesFeedActivity
import com.danser.workshop4_login.di.module.NotesFeedModule
import dagger.Subcomponent


@Subcomponent(modules = [NotesFeedModule::class])
interface NotesFeedComponent {

    @Subcomponent.Factory
    interface Factory {
        fun create(): NotesFeedComponent
    }

    fun inject(activity: NotesFeedActivity)
}

