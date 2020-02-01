package com.danser.workshop4_login.feature.feed

import android.os.Bundle
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.danser.workshop4_login.feature.feed.view.NoteView
import com.danser.workshop4_login.NotesApplication.Companion.injector
import com.danser.workshop4_login.R
import com.danser.workshop4_login.di.module.NotesFeedComponent
import com.danser.workshop4_login.feature.feed.presentation.NotesFeedPresentationModel
import kotlinx.android.synthetic.main.activity_main.*
import java.util.Collections.swap


interface NotesFeedView {
    fun update(model: NotesFeedViewModel)
}

class NotesFeedActivity : AppCompatActivity(),
    NotesFeedView, NotesFeedComponent by injector.notesFeedModule {

    private lateinit var model: NotesFeedPresentationModel

    private lateinit var adapter: NotesAdapter
    private lateinit var layoutManager: LinearLayoutManager

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        initPresentationModel()
        initUi()
    }

    override fun update(model: NotesFeedViewModel) {
        adapter.items = model.notes
        adapter.notifyDataSetChanged()
    }

    private fun initPresentationModel() {

        model = ViewModelProviders.of(this, notesFeedPresentationFactory)[NotesFeedPresentationModel::class.java]

        val observer = Observer<NotesFeedViewModel> { viewModel -> update(viewModel) }
        model.modelLiveData.observe(this, observer)
    }

    private fun initUi() {
        adapter =
            NotesAdapter()
        layoutManager = LinearLayoutManager(this, RecyclerView.VERTICAL, false)
        rvList.adapter = adapter
        rvList.layoutManager = layoutManager

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

        setItemTouchHelper()

        vFab.setOnClickListener {
            model.onAddNoteClicked()
        }
    }

    private fun setItemTouchHelper() {
        val callback = object : ItemTouchHelper.SimpleCallback(
            ItemTouchHelper.UP or ItemTouchHelper.DOWN,
            ItemTouchHelper.START or ItemTouchHelper.END
        ) {

            override fun onMove(
                recyclerView: RecyclerView,
                viewHolder: RecyclerView.ViewHolder,
                target: RecyclerView.ViewHolder
            ): Boolean {
                val fromPosition = viewHolder.adapterPosition
                val toPosition = target.adapterPosition
                if (fromPosition < toPosition) {
                    for (i in fromPosition until toPosition) {
                        adapter.items =
                            adapter.items.toMutableList().apply { swap(adapter.items, i, i + 1) }
                    }
                } else {
                    for (i in fromPosition downTo toPosition + 1) {
                        adapter.items =
                            adapter.items.toMutableList().apply { swap(adapter.items, i, i - 1) }
                    }
                }
                adapter.notifyItemMoved(fromPosition, toPosition)
                return true
            }

            override fun onSwiped(viewHolder: RecyclerView.ViewHolder, direction: Int) {

            }
        }

        val itemTouchHelper = ItemTouchHelper(callback)
        itemTouchHelper.attachToRecyclerView(rvList)
    }

    class NotesAdapter(
        var items: List<NoteView.Model> = emptyList()
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
            return NoteViewHolder(
                view
            )
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
