package com.danser.workshop4_login.feature.feed

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.danser.workshop4_login.NotesApplication.Companion.injector
import com.danser.workshop4_login.R
import com.danser.workshop4_login.feature.feed.di.NotesFeedComponent
import com.danser.workshop4_login.feature.feed.presentation.NotesFeedPresentationModel
import com.danser.workshop4_login.feature.feed.view.FeedItemTouchHelperCallback
import com.danser.workshop4_login.feature.feed.view.NoteView
import com.danser.workshop4_login.feature.feed.view.NotesAdapter
import com.danser.workshop4_login.presentation.SingleEvent
import com.danser.workshop4_login.router.Router
import com.danser.workshop4_login.router.command.RouterCommand
import kotlinx.android.synthetic.main.activity_feed.*


class NotesFeedActivity : AppCompatActivity(), NotesFeedComponent by injector.notesFeedModule {

    private lateinit var presentation: NotesFeedPresentationModel

    private lateinit var adapter: NotesAdapter
    private lateinit var layoutManager: LinearLayoutManager

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_feed)

        initPresentationModel()
        initUi()
    }

    private fun update(model: NotesFeedViewModel) {
        adapter.items = model.notes
        adapter.notifyDataSetChanged()
    }

    private fun perform(routerCommand: RouterCommand) {
        routerCommand.perform(this, Router)
    }

    private fun initPresentationModel() {

        presentation = ViewModelProviders.of(
            this,
            notesFeedPresentationFactory
        )[NotesFeedPresentationModel::class.java]

        presentation.modelLiveData.observe(this, Observer { viewModel: NotesFeedViewModel ->
            update(viewModel)
        })
        presentation.routerLiveData.observe(this, Observer { event: SingleEvent<RouterCommand> ->
            val routerCommand = event.getContentIfNotHandled()
            if (routerCommand != null) perform(routerCommand)
        })
    }

    private fun initUi() {
        adapter = NotesAdapter { presentation.onNoteClicked(it) }
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
            presentation.onAddNoteClicked()
        }
    }

    private fun setItemTouchHelper() {
        val itemTouchHelper = ItemTouchHelper(FeedItemTouchHelperCallback(adapter))
        itemTouchHelper.attachToRecyclerView(rvList)
    }
}

data class NotesFeedViewModel(
    val notes: List<NoteView.Model>
)
