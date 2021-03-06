package com.danser.workshop4_login.data

import com.danser.workshop4_login.data.INotesRepository
import com.danser.workshop4_login.data.NotesMockRepository
import com.danser.workshop4_login.data.data.NoteEntity
import com.danser.workshop4_login.data.db.NotesDatabase
import com.danser.workshop4_login.domain.Note

class NotesRepository(
    database: NotesDatabase
) : INotesRepository {

    private val dao = database.getNoteDataDao()

    init {
        if (getNotes().isEmpty()) {
            NotesMockRepository().getNotes().forEach { note -> addNote(note) }
        }
    }

    override fun getNotes(): List<Note> = dao
        .getNotes()
        .map { entity ->
            Note(
                id = entity.id.toString(),
                title = entity.title,
                text = entity.text
            )
        }

    override fun addNote(note: Note) {
        val entity = NoteEntity(
            title = note.title,
            text = note.text
        )
        dao.insert(entity)
    }
}
