package com.danser.workshop4_login.di.module

import com.danser.workshop4_login.NotesRepository
import com.danser.workshop4_login.data.INotesRepository
import com.danser.workshop4_login.data.IPrefsRepository
import com.danser.workshop4_login.data.PrefsRepository
import com.danser.workshop4_login.data.db.NotesDatabaseProvider
import org.koin.android.ext.koin.androidContext
import org.koin.dsl.module

val appModule = module {
    single<INotesRepository> { NotesRepository(get()) }
    single {
        NotesDatabaseProvider(
            context = androidContext(),
            allowMainThreadQueries = true
        ).getNotesDatabase()
    }
    single<IPrefsRepository> { PrefsRepository(androidContext()) }
}
