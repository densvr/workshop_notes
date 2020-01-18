package com.danser.workshop4_login.di.module

import android.content.Context
import com.danser.workshop4_login.NotesRepository
import com.danser.workshop4_login.NotesVMFactory
import com.danser.workshop4_login.data.db.NotesDatabaseProvider
import com.danser.workshop4_login.di.NoteFeedPresentationDependencies
import com.danser.workshop4_login.di.NotesFeedPresentationFactory
import dagger.Module
import dagger.Provides

@Module
class NotesFeedModule {

    @Provides
    fun providesNotesFeedPresentationFactory(context: Context): NotesFeedPresentationFactory = NotesFeedPresentationFactory(
        NoteFeedPresentationDependencies(
            notesRepository = NotesRepository(
                database = NotesDatabaseProvider(
                    context = context,
                    allowMainThreadQueries = true
                ).getNotesDatabase()
            ),
            notesVMFactory = NotesVMFactory()
        )
    )
}
