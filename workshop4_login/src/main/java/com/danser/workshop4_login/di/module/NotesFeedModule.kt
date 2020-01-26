package com.danser.workshop4_login.di.module

import android.content.Context
import com.danser.workshop4_login.NotesFeedActivity
import com.danser.workshop4_login.NotesRepository
import com.danser.workshop4_login.NotesVMFactory
import com.danser.workshop4_login.data.INotesRepository
import com.danser.workshop4_login.data.db.NotesDatabase
import com.danser.workshop4_login.data.db.NotesDatabaseProvider
import com.danser.workshop4_login.di.NoteFeedPresentationDependencies
import com.danser.workshop4_login.di.NotesFeedPresentationFactory
import dagger.Module
import dagger.Provides
import org.koin.core.qualifier.named
import org.koin.dsl.module


val notesFeedModule = module {
    // declare a scope for DetailActivity
   /* scope(named<NotesFeedActivity>()) {
        scoped { NotesFeedPresentationFactory(get()) }
        scoped { NoteFeedPresentationDependencies(get(), get()) }
        scoped { NotesVMFactory() }
    }*/

    single { NotesFeedPresentationFactory(get()) }
    single { NoteFeedPresentationDependencies(get(), get()) }
    single { NotesVMFactory() }
}

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
