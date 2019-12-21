package com.danser.workshop4_login

import androidx.test.platform.app.InstrumentationRegistry
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.danser.workshop4_login.data.NotesRepository
import com.danser.workshop4_login.data.data.NoteEntity
import com.danser.workshop4_login.data.db.NotesDatabaseProvider

import org.junit.Test
import org.junit.runner.RunWith

import org.junit.Assert.*

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

    @Test
    fun useAppContext() {

        val notes = notesRepo.getNotes()
        val entities = notes.mapIndexed { pos, note ->
            NoteEntity(
                id = pos.toString(),
                title = note.title,
                text = note.text
            )
        }

        database.apply {
            entities.forEach { getNoteDataDao1().insert(it) }
            val savedEntities = getNoteDataDao1().getAll()
            assertEquals(entities, savedEntities)
        }
    }
}
