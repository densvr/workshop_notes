package com.danser.workshop4_login.feature.card.presentation

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.danser.workshop4_login.domain.Note
import com.danser.workshop4_login.feature.card.NoteCardViewModel
import com.danser.workshop4_login.feature.card.view.adapter.TextItem

class NotesCardPresentationModel(
    private val deps: NoteCardPresentationDependencies
) : ViewModel() {

    val modelLiveData by lazy {
        MutableLiveData<NoteCardViewModel>()
    }

    private var model: Model = Model(null)

    init {
        val note = deps.notesRepository.getNotes().firstOrNull { it.id == deps.args.noteId }
        if (note != null) {
            model = Model(note)
            update()
        }
    }

    private fun update(mapper: Model.() -> Model = { this }) {
        model = model.mapper()
        val viewModel = NoteCardViewModel(
            listOfNotNull(
                model.note?.text?.let { TextItem(it) }
            )
        )
        modelLiveData.value = viewModel
    }

    data class Model(
        val note: Note?
    )
}
