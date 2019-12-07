package com.danser.workshop4_login

import android.graphics.Color
import android.os.Bundle
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    private lateinit var adapter: NotesAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        adapter = NotesAdapter(ITEMS)
        rvList.adapter = adapter
        rvList.layoutManager = LinearLayoutManager(this, RecyclerView.VERTICAL, false)
    }

    class NotesAdapter(var items: List<NoteView.Model>) : RecyclerView.Adapter<NoteViewHolder>() {

        override fun getItemCount(): Int = items.size

        override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NoteViewHolder {
            val view = NoteView(parent.context).apply {
                layoutParams = RecyclerView.LayoutParams(
                    RecyclerView.LayoutParams.MATCH_PARENT,
                    RecyclerView.LayoutParams.WRAP_CONTENT
                )
            }
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

    companion object {

        private val ITEMS = listOf(
            "note 1",
            "note 2",
            "note 3",
            "note 4",
            "the last note"
        ).mapIndexed { pos, title ->
            val color = if (pos % 2 == 0) Color.TRANSPARENT else Color.LTGRAY
            NoteView.Model(
                title = title,
                text = "Ипотечный кредит на 4 года 240тр ставка 55тр, переплата 220-240тр\n15:10\tExecuting tasks: [:workshop4_login:assembleDebug] in project /Users/danser/workshops\n15:10\tExecuting tasks: [:workshop4_login:assembleDebug] in project /Users/danser/workshops",
                backgroundColor = color
            )
        }
    }
}
