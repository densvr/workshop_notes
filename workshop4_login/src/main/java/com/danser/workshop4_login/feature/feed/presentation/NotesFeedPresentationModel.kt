package com.danser.workshop4_login.feature.feed.presentation

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.danser.workshop4_login.feature.feed.NotesFeedViewModel
import com.danser.workshop4_login.domain.Note
import com.danser.workshop4_login.feature.card.NoteCardArguments
import com.danser.workshop4_login.presentation.SingleEvent
import com.danser.workshop4_login.router.command.NoteCardCommand
import com.danser.workshop4_login.router.command.RouterCommand

class NotesFeedPresentationModel(
    private val deps: NoteFeedPresentationDependencies
) : ViewModel() {

    val modelLiveData by lazy {
        MutableLiveData<NotesFeedViewModel>()
    }

    val routerLiveData by lazy {
        MutableLiveData<SingleEvent<RouterCommand>>()
    }

    private var model: Model

    init {
        model = Model(deps.notesRepository.getNotes())
        update()
    }

    fun onNoteClicked(note: Note) {
        val arguments = NoteCardArguments(
            noteId = note.id
        )
        perform(NoteCardCommand(arguments))
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

    private fun perform(command: RouterCommand) {
        routerLiveData.postValue(SingleEvent(command))
    }

    data class Model(
        val notes: List<Note>
    )

    companion object {
        private val NOTE_TO_ADD = Note(
            id = "",
            title = "THIS IS NOTE",
            text = "which has been added manually"
        )
    }
}
