package com.danser.workshop4_login.di.module

import com.danser.workshop4_login.NotesVMFactory
import com.danser.workshop4_login.di.NoteFeedPresentationDependencies
import com.danser.workshop4_login.di.NotesFeedPresentationFactory


class NotesFeedModule(component: ApplicationComponent) : NotesFeedComponent {

    override val notesFeedPresentationFactory: NotesFeedPresentationFactory =
        NotesFeedPresentationFactory(
            NoteFeedPresentationDependencies(
                notesRepository = component.notesRepo,
                notesVMFactory = NotesVMFactory()
            )
        )
}

interface NotesFeedComponent {
    val notesFeedPresentationFactory: NotesFeedPresentationFactory
}
