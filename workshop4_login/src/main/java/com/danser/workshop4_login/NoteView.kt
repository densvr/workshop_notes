package com.danser.workshop4_login

import android.content.Context
import android.util.AttributeSet
import android.widget.FrameLayout
import androidx.annotation.ColorInt
import kotlinx.android.synthetic.main.view_note.view.*

class NoteView @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null,
    defStyleAttr: Int = -1
) : FrameLayout(context, attrs, defStyleAttr) {

    init {
        inflate(context, R.layout.view_note, this)
    }

    fun update(model: Model) {
        tvTitle.text = model.title
        tvText.text = model.text
        setBackgroundColor(model.backgroundColor)
    }

    data class Model(
        val title: String,
        val text: String,
        @ColorInt val backgroundColor: Int
    )
}
