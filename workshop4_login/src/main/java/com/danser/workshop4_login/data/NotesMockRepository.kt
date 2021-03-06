package com.danser.workshop4_login.data

import com.danser.workshop4_login.domain.Note

interface INotesRepository {
    fun getNotes(): List<Note>
    fun addNote(note: Note)
}

class NotesMockRepository() : INotesRepository {

    private val notes: MutableList<Note>

    init {
        val text =
            "Ипотечный кредит на 4 года 240тр ставка 55тр, переплата 220-240тр\n15:10\tExecuting tasks: [:workshop4_login:assembleDebug] in project /Users/danser/workshops\n15:10\tExecuting tasks: [:workshop4_login:assembleDebug] in project /Users/danser/workshops"
        notes = listOf(
            "hahaha",
            "hahaha",
            "hahaha",
            "hahaha",
            "hahaha",
            "hahaha",
            "hahaha",
            "hahaha",
            "the last note"
        ).map {
            Note(
                id = id++.toString(),
                title = it,
                text = text
            )
        }.toMutableList()
    }

    override fun getNotes(): List<Note> = notes

    override fun addNote(note: Note) {
        notes.add(0, note)
    }

    private var id = 0
}
