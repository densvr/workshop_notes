package com.danser.workshop4_login.ext

import android.app.Activity
import com.danser.workshop4_login.di.component.ApplicationComponent
import com.danser.workshop4_login.di.component.IApplicationComponentProvider

fun Activity.getApplicationComponent(): ApplicationComponent =
    (application as IApplicationComponentProvider).get()






/*

DaggerApplicationComponent [root]
    ApplicationModule
        - Context

    NotesFeedComponent
        NotesFeedModule
            - NotesVMFactory
            - INotesRepository


    LoginComponent
        LoginModule
            - PrefsRepo
*/
