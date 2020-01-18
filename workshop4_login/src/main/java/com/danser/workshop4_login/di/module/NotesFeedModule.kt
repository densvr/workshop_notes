package com.danser.workshop4_login.di.module

import android.content.Context
import com.danser.workshop4_login.NotesRepository
import com.danser.workshop4_login.NotesVMFactory
import com.danser.workshop4_login.data.INotesRepository
import com.danser.workshop4_login.data.db.NotesDatabase
import com.danser.workshop4_login.data.db.NotesDatabaseProvider
import dagger.Module
import dagger.Provides

@Module
class NotesFeedModule {

    @Provides
    fun provideNotesVmFactory(): NotesVMFactory = NotesVMFactory()

    @Provides
    fun provideNotesRepo(repo: NotesRepository): INotesRepository = repo

    @Provides
    fun provideNotesDatabase(
        context: Context
    ): NotesDatabase = NotesDatabaseProvider(
        context = context,
        allowMainThreadQueries = true
    ).getNotesDatabase()
}
