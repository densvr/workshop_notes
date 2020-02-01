package com.danser.workshop4_login.feature.feed.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.danser.workshop4_login.data.INotesRepository

class NotesFeedPresentationFactory(
    private val dependencies: NoteFeedPresentationDependencies
) : ViewModelProvider.Factory {

    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return modelClass.getConstructor(NoteFeedPresentationDependencies::class.java)
            .newInstance(dependencies)
    }
}

class NoteFeedPresentationDependencies(
    val notesRepository: INotesRepository,
    val notesVMFactory: NotesFeedVMFactory
)
