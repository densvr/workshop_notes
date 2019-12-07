package com.danser.workshop4_login

import android.os.Bundle
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.danser.workshop4_login.presentation.NotesFeedPresenter
import kotlinx.android.synthetic.main.activity_main.*


interface NotesFeedView {
    fun update(model: NotesFeedViewModel)
}

class NotesFeedActivity : AppCompatActivity(), NotesFeedView {

    private lateinit var adapter: NotesAdapter
    private val presenter: NotesFeedPresenter = NotesFeedPresenter()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        adapter = NotesAdapter()
        rvList.adapter = adapter
        rvList.layoutManager = LinearLayoutManager(this, RecyclerView.VERTICAL, false)

        rvList.addOnScrollListener(object : RecyclerView.OnScrollListener() {
            override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
                if (dy > 0) {
                    vFab.hide()
                } else {
                    vFab.show()
                }
                super.onScrolled(recyclerView, dx, dy)
            }
        })
    }

    override fun update(model: NotesFeedViewModel) {
        adapter.items = model.notes
        adapter.notifyDataSetChanged()
    }

    override fun onStart() {
        super.onStart()
        presenter.bindView(this)
    }

    override fun onStop() {
        super.onStop()
        presenter.unbindView()
    }

    class NotesAdapter(var items: List<NoteView.Model> = emptyList()) :
        RecyclerView.Adapter<NoteViewHolder>() {

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
}

data class NotesFeedViewModel(
    val notes: List<NoteView.Model>
)
