package com.danser.workshop4_login

import androidx.test.platform.app.InstrumentationRegistry
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.danser.workshop4_login.data.NotesRepository
import com.danser.workshop4_login.data.data.NoteEntity
import com.danser.workshop4_login.data.db.NotesDatabaseProvider
import com.danser.workshop4_login.domain.Note
import org.junit.After

import org.junit.Test
import org.junit.runner.RunWith

import org.junit.Assert.*
import org.junit.Before

/**
 * Instrumented test, which will execute on an Android device.
 *
 * See [testing documentation](http://d.android.com/tools/testing).
 */
@RunWith(AndroidJUnit4::class)
class ExampleInstrumentedTest {

    private val context = InstrumentationRegistry.getInstrumentation().targetContext
    private val database = NotesDatabaseProvider(context).getNotesDatabase()
    private val notesRepo = NotesRepository()

    @Before
    fun before() {
        database.clearAllTables()
    }

    @After
    fun after() {
        database.clearAllTables()
    }

    @Test
    fun checkOrderlessReadNotesAfterWriteToDB() {

        val notes = notesRepo.getNotes().toSet()
        val entities = notes.mapIndexed { pos, note ->
            NoteEntity(
                id = pos.toLong(),
                title = note.title,
                text = note.text
            )
        }

        val savedEntities = database.run {
            entities.forEach { getNoteDataDao().insert(it) }
            getNoteDataDao().getAll()
        }
        val savedNotes = savedEntities.map { entity ->
            Note(
                title = entity.title,
                text = entity.text
            )
        }.toSet()

        assertEquals(notes, savedNotes)
    }
}
