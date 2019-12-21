package com.danser.workshop4_login

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
            NotesMockRepository().getNotes().forEach { note ->
                addNote(
                    entity = NoteEntity(
                        title = note.title,
                        text = note.text
                    )
                )
            }
        }
    }

    override fun getNotes(): List<Note> = dao
        .getNotes()
        .map { entity ->
            Note(
                title = entity.title,
                text = entity.text
            )
        }

    override fun addNote(entity: NoteEntity) = dao
        .insert(entity)

}