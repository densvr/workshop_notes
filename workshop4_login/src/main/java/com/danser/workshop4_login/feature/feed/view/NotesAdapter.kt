package com.danser.workshop4_login.feature.feed.view

import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.danser.workshop4_login.domain.Note

class NotesAdapter(
    var items: List<NoteView.Model> = emptyList(),
    private val onClick: (Note) -> Unit
) : RecyclerView.Adapter<NoteViewHolder>() {

    override fun getItemCount(): Int = items.size

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NoteViewHolder {
        val view = NoteView(
            parent.context
        ).apply {
            layoutParams = RecyclerView.LayoutParams(
                RecyclerView.LayoutParams.MATCH_PARENT,
                RecyclerView.LayoutParams.WRAP_CONTENT
            )
        }
        view.onClick = onClick
        return NoteViewHolder(view)
    }

    override fun onBindViewHolder(holder: NoteViewHolder, position: Int) {
        val model = items[position]
        holder.update(model)
    }
}

class NoteViewHolder(private val view: NoteView) : RecyclerView.ViewHolder(view) {

    fun update(model: NoteView.Model) {
        view.update(model)
    }
}
