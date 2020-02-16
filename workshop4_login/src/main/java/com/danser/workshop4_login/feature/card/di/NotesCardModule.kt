package com.danser.workshop4_login.feature.card.di

import com.danser.workshop4_login.di.ApplicationComponent
import com.danser.workshop4_login.feature.card.NoteCardArguments
import com.danser.workshop4_login.feature.card.presentation.NoteCardPresentationDependencies
import com.danser.workshop4_login.feature.card.presentation.NotesCardPresentationFactory


class NotesCardModule(
    component: ApplicationComponent,
    private val args: NoteCardArguments
) : NotesCardComponent {

    override val notesCardPresentationFactory: NotesCardPresentationFactory =
        NotesCardPresentationFactory(
            NoteCardPresentationDependencies(
                notesRepository = component.notesRepo,
                args = args
            )
        )
}

interface NotesCardComponent {
    val notesCardPresentationFactory: NotesCardPresentationFactory
}
