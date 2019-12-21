package com.danser.workshop4_login.data.data

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class NoteEntity(
    @PrimaryKey(autoGenerate = true) val id: String,
    val title: String,
    val text: String
)
