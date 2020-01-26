package com.danser.workshop4_login.di.module

import com.danser.workshop4_login.NotesFeedActivity
import com.danser.workshop4_login.NotesVMFactory
import com.danser.workshop4_login.di.NoteFeedPresentationDependencies
import com.danser.workshop4_login.di.NotesFeedPresentationFactory
import com.danser.workshop4_login.presentation.NotesFeedPresentationModel
import org.koin.core.qualifier.named
import org.koin.dsl.module

val notesFeedModule = module {
    scope(named<NotesFeedActivity>()) {

        factory { NotesFeedPresentationModel(get()) }
        scoped { NotesFeedPresentationFactory(get()) }
        scoped { NoteFeedPresentationDependencies(get(), get()) }
        scoped { NotesVMFactory() }
    }
}
