package com.danser.workshop4_login.di.module

import android.content.Context
import com.danser.workshop4_login.data.INotesRepository
import com.danser.workshop4_login.data.IPrefsRepository
import com.danser.workshop4_login.data.NotesRepository
import com.danser.workshop4_login.data.PrefsRepository
import com.danser.workshop4_login.data.db.NotesDatabaseProvider


class ApplicationModule(context: Context): ApplicationComponent {

    override val notesRepo: INotesRepository = NotesRepository(
        NotesDatabaseProvider(
            context = context,
            allowMainThreadQueries = true
        ).getNotesDatabase()
    )

    override val prefsRepo: IPrefsRepository = PrefsRepository(context)
}


interface ApplicationComponent {
    val notesRepo: INotesRepository
    val prefsRepo: IPrefsRepository
}
