package com.danser.workshop4_login.data.db

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy.REPLACE
import androidx.room.Query
import com.danser.workshop4_login.data.data.NoteEntity

@Dao
interface INoteDataDao {

    @Query("SELECT * from NoteEntity")
    fun getNotes(): List<NoteEntity>

    @Insert(onConflict = REPLACE)
    fun insert(entity: NoteEntity)
}
