package com.danser.workshop4_login.data

import com.danser.workshop4_login.domain.Note

interface INotesRepository {
    fun getNotes(): List<Note>
}

class NotesRepository() : INotesRepository {

    override fun getNotes(): List<Note> {
        val text =
            "Ипотечный кредит на 4 года 240тр ставка 55тр, переплата 220-240тр\n15:10\tExecuting tasks: [:workshop4_login:assembleDebug] in project /Users/danser/workshops\n15:10\tExecuting tasks: [:workshop4_login:assembleDebug] in project /Users/danser/workshops"
        return listOf(
            "note 1",
            "note 2",
            "note 3",
            "note 4",
            "note 1",
            "note 2",
            "note 3",
            "note 4",
            "the last note"
        ).map {
            Note(
                title = it,
                text = text
            )
        }
    }

}
