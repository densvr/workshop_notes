package com.danser.workshop4_login.di

import android.content.Context
import com.danser.workshop4_login.NotesApplication
import com.danser.workshop4_login.feature.card.NoteCardArguments
import com.danser.workshop4_login.feature.card.di.NotesCardModule
import com.danser.workshop4_login.feature.feed.di.NotesFeedModule

class Injector(context: Context)  {

    val applicationModule: ApplicationModule =
        ApplicationModule(context)
    val notesFeedModule: NotesFeedModule by lazy {
        NotesFeedModule(applicationModule)
    }
    fun getNotesCardModule(args: NoteCardArguments): NotesCardModule = NotesCardModule(
        component = applicationModule,
        args = args
    )
}


val injector: Injector get() = NotesApplication.injector
