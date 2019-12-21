package com.danser.workshop4_login.presentation

import android.content.Context
import com.danser.workshop4_login.NotesFeedView
import com.danser.workshop4_login.NotesRepository
import com.danser.workshop4_login.NotesVMFactory
import com.danser.workshop4_login.data.INotesRepository
import com.danser.workshop4_login.data.NotesMockRepository
import com.danser.workshop4_login.data.db.INotesDatabaseProvider
import com.danser.workshop4_login.data.db.NotesDatabase
import com.danser.workshop4_login.data.db.NotesDatabaseProvider
import com.danser.workshop4_login.domain.Note

class NotesFeedPresenter(
    notesRepository: INotesRepository,
    private val notesVMFactory: NotesVMFactory
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
