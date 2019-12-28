package com.danser.workshop4_login.presentation

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.danser.workshop4_login.NotesFeedViewModel
import com.danser.workshop4_login.NotesVMFactory
import com.danser.workshop4_login.data.INotesRepository
import com.danser.workshop4_login.data.NotesMockRepository
import com.danser.workshop4_login.domain.Note

class NotesFeedPresentationModel(
    notesRepository: INotesRepository = NotesMockRepository(),
/*
    notesRepository = NotesRepository(
        database = NotesDatabaseProvider(
            context = this,
            allowMainThreadQueries = true
        ).getNotesDatabase()
    )*/
    private val notesVMFactory: NotesVMFactory = NotesVMFactory()
) : ViewModel() {

    val modelLiveData by lazy {
        MutableLiveData<NotesFeedViewModel>()
    }

    private var model: Model

    init {
        model = Model(notesRepository.getNotes())
        update()
    }

    private fun update() {
        val viewModel = notesVMFactory.toViewModel(model)
        modelLiveData.postValue(viewModel)
    }

    class Model(
        val notes: List<Note>
    )
}
