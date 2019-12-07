package com.danser.workshop4_login

import android.graphics.Color
import androidx.annotation.ColorInt
import com.danser.workshop4_login.domain.Note
import com.danser.workshop4_login.presentation.NotesFeedPresenter

class NotesVMFactory() {

    fun toViewModel(model: NotesFeedPresenter.Model) = NotesFeedViewModel(
        notes = getViewModel(model.notes)
    )

    private fun getViewModel(notes: List<Note>): List<NoteView.Model> = notes.mapIndexed { pos, note ->
        val color = if (pos % 2 == 0) Color.TRANSPARENT else Color.LTGRAY
        getViewModel(note, color)
    }

    private fun getViewModel(note: Note, @ColorInt color: Int): NoteView.Model = NoteView.Model(
        title = note.title,
        text = note.text,
        backgroundColor = color
    )

}
