package com.danser.workshop4_login.di.module

import com.danser.workshop4_login.feature.feed.presentation.NotesFeedVMFactory
import com.danser.workshop4_login.feature.feed.presentation.NoteFeedPresentationDependencies
import com.danser.workshop4_login.feature.feed.presentation.NotesFeedPresentationFactory


class NotesFeedModule(component: ApplicationComponent) : NotesFeedComponent {

    override val notesFeedPresentationFactory: NotesFeedPresentationFactory =
        NotesFeedPresentationFactory(
            NoteFeedPresentationDependencies(
                notesRepository = component.notesRepo,
                notesVMFactory = NotesFeedVMFactory()
            )
        )
}

interface NotesFeedComponent {
    val notesFeedPresentationFactory: NotesFeedPresentationFactory
}
