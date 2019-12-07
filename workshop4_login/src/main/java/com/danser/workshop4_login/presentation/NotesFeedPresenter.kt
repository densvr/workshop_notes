package com.danser.workshop4_login.presentation

import com.danser.workshop4_login.NotesFeedView
import com.danser.workshop4_login.NotesVMFactory
import com.danser.workshop4_login.data.INotesRepository
import com.danser.workshop4_login.data.NotesRepository
import com.danser.workshop4_login.domain.Note

class NotesFeedPresenter(
    notesRepository: INotesRepository = NotesRepository(),
    private val notesVMFactory: NotesVMFactory = NotesVMFactory()
) {

    private var model: Model
    private var view: NotesFeedView? = null

    init {
        model = Model(notesRepository.getNotes())
    }

    fun bindView(view: NotesFeedView) {
        this.view = view
        update()
    }

    fun unbindView() {
        this.view = null
    }

    private fun update() {
        val viewModel = notesVMFactory.toViewModel(model)
        view?.update(viewModel)
    }

    class Model(
        val notes: List<Note>
    )
}
