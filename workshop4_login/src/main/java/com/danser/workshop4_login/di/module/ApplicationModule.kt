package com.danser.workshop4_login.di.module

import android.content.Context
import com.danser.workshop4_login.NotesRepository
import com.danser.workshop4_login.data.INotesRepository
import com.danser.workshop4_login.data.db.NotesDatabaseProvider
import dagger.Module
import dagger.Provides
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
}



@Module
class ApplicationModule(private val context: Context) {

    @Provides
    fun provideContext(): Context = context
}
