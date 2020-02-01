package com.danser.workshop4_login.feature.feed.presentation

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.danser.workshop4_login.feature.feed.NotesFeedViewModel
import com.danser.workshop4_login.domain.Note

class NotesFeedPresentationModel(
    private val deps: NoteFeedPresentationDependencies
) : ViewModel() {

    val modelLiveData by lazy {
        MutableLiveData<NotesFeedViewModel>()
    }

    private var model: Model

    init {
        model =
            Model(
                deps.notesRepository.getNotes()
            )
        update()
    }

    fun onAddNoteClicked() {
        deps.notesRepository.addNote(NOTE_TO_ADD)
        val newNotes = deps.notesRepository.getNotes()
        update { copy(notes = newNotes) }
    }

    private fun update(mapper: Model.() -> Model = { this }) {
        model = model.mapper()
        val viewModel = deps.notesVMFactory.toViewModel(model)
        modelLiveData.value = viewModel
    }

    data class Model(
        val notes: List<Note>
    )

    companion object {
        private val NOTE_TO_ADD = Note(
            title = "THIS IS NOTE",
            text = "which has been added manually"
        )
    }
}
