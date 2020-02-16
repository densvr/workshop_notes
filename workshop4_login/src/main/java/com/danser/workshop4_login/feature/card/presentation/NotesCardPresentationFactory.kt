package com.danser.workshop4_login.feature.card.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.danser.workshop4_login.data.INotesRepository
import com.danser.workshop4_login.feature.card.NoteCardArguments

class NotesCardPresentationFactory(
    private val dependencies: NoteCardPresentationDependencies
) : ViewModelProvider.Factory {

    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return modelClass.getConstructor(NoteCardPresentationDependencies::class.java)
            .newInstance(dependencies)
    }
}

class NoteCardPresentationDependencies(
    val notesRepository: INotesRepository,
    val args: NoteCardArguments
)
