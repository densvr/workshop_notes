package com.danser.workshop4_login.di.module

import com.danser.workshop4_login.NotesFeedActivity
import com.danser.workshop4_login.NotesVMFactory
import com.danser.workshop4_login.di.NoteFeedPresentationDependencies
import com.danser.workshop4_login.di.NotesFeedPresentationFactory
import org.koin.core.qualifier.named
import org.koin.dsl.module

val notesFeedModule = module {
    // declare a scope for DetailActivity
    scope(named<NotesFeedActivity>()) {
        scoped { NotesFeedPresentationFactory(get()) }
        scoped { NoteFeedPresentationDependencies(get(), get()) }
        scoped { NotesVMFactory() }
    }
}
