package com.danser.workshop4_login.di.module

import android.content.Context
import com.danser.workshop4_login.NotesRepository
import com.danser.workshop4_login.NotesVMFactory
import com.danser.workshop4_login.data.INotesRepository
import com.danser.workshop4_login.data.db.NotesDatabase
import com.danser.workshop4_login.data.db.NotesDatabaseProvider
import com.danser.workshop4_login.di.NoteFeedPresentationDependencies
import com.danser.workshop4_login.di.NotesFeedPresentationFactory
import dagger.Module
import dagger.Provides
import javax.inject.Named

@Module
class NotesFeedModule {

    @Provides
    fun providesNotesFeedPresentationFactory(
        notesRepo: INotesRepository,
        vmFactory: NotesVMFactory
    ): NotesFeedPresentationFactory = NotesFeedPresentationFactory(
        NoteFeedPresentationDependencies(
            notesRepository = notesRepo,
            notesVMFactory = vmFactory
        )
    )

    @Provides
    fun provideNotesRepo(database: NotesDatabase): INotesRepository = NotesRepository(
        database = database
    )

    @Provides
    fun provideNotesVmFactory(): NotesVMFactory = NotesVMFactory()


    @Provides
    @Named(AllowMainThreadQueries)
    fun provideAllowMainThreadQueries(): Boolean = true


    @Provides
    fun provideSomeSetting(): Boolean = false

    @Provides
    fun provideNotesDatabase(
        context: Context,
        @Named(AllowMainThreadQueries) allowMainThreadQueries: Boolean
    ): NotesDatabase = NotesDatabaseProvider(
        context = context,
        allowMainThreadQueries = allowMainThreadQueries
    ).getNotesDatabase()

    companion object {
        const val AllowMainThreadQueries = "AllowMainThreadQueries"
    }
}
