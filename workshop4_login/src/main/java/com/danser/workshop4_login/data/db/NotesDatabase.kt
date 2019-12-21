package com.danser.workshop4_login.data.db

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.danser.workshop4_login.data.data.NoteEntity

@Database(entities = [NoteEntity::class], version = 1)
abstract class NotesDatabase : RoomDatabase() {

    abstract fun getNoteDataDao(): INoteDataDao

}

interface INotesDatabaseProvider {
    fun getNotesDatabase(): NotesDatabase
}

class NotesDatabaseProvider(
    private val context: Context,
    private val allowMainThreadQueries: Boolean = false
) : INotesDatabaseProvider {

    override fun getNotesDatabase(): NotesDatabase = Room
        .databaseBuilder(
            context.applicationContext,
            NotesDatabase::class.java,
            "notes.db"
        )
        .apply { if (allowMainThreadQueries) allowMainThreadQueries() }
        .build()
}
