package com.danser.workshop4_login.feature.feed.view

import android.content.Context
import android.util.AttributeSet
import android.widget.FrameLayout
import androidx.annotation.ColorInt
import com.danser.workshop4_login.R
import com.danser.workshop4_login.domain.Note
import kotlinx.android.synthetic.main.view_note.view.*

class NoteView @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null,
    defStyleAttr: Int = -1
) : FrameLayout(context, attrs, defStyleAttr) {

    var onClick: (note: Note) -> Unit = {}

    init {
        inflate(context, R.layout.view_note, this)
    }

    fun update(model: Model) {
        tvTitle.text = model.title
        tvText.text = model.text
        setBackgroundColor(model.backgroundColor)
        setOnClickListener { onClick(model.payload) }
    }

    data class Model(
        val title: String,
        val text: String,
        @ColorInt val backgroundColor: Int,
        val payload: Note
    )
}
