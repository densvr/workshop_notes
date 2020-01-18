package com.danser.workshop4_login.di

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.danser.workshop4_login.NotesVMFactory
import com.danser.workshop4_login.data.INotesRepository
import javax.inject.Inject

class NotesFeedPresentationFactory @Inject constructor(
    private val dependencies: NoteFeedPresentationDependencies
): ViewModelProvider.Factory {

    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return modelClass.getConstructor(NoteFeedPresentationDependencies::class.java)
            .newInstance(dependencies)
    }
}

class NoteFeedPresentationDependencies @Inject constructor(
    val notesRepository: INotesRepository,
    val notesVMFactory: NotesVMFactory
)
