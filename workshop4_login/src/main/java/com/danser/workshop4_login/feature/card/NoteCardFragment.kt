package com.danser.workshop4_login.feature.card

import android.os.Bundle
import android.os.Parcelable
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import com.danser.workshop4_login.R
import com.danser.workshop4_login.di.injector
import com.danser.workshop4_login.feature.card.di.NotesCardComponent
import com.danser.workshop4_login.feature.card.presentation.NotesCardPresentationFactory
import com.danser.workshop4_login.feature.card.presentation.NotesCardPresentationModel
import com.danser.workshop4_login.feature.card.view.adapter.TextAdapter
import com.example.delegateadapter.delegate.diff.DiffUtilCompositeAdapter
import com.example.delegateadapter.delegate.diff.IComparableItem
import kotlinx.android.parcel.Parcelize
import kotlinx.android.synthetic.main.fragment_notes_card.*

class NoteCardFragment : Fragment() {

    private lateinit var component: NotesCardComponent

    private val notesCardPresentationFactory: NotesCardPresentationFactory by lazy {
        component.notesCardPresentationFactory
    }

    private val adapter: DiffUtilCompositeAdapter by lazy { getCompositeAdapter() }

    private lateinit var presentation: NotesCardPresentationModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val args = arguments?.getParcelable<NoteCardArguments>(ARGUMENTS_KEY)!!

        component = injector.getNotesCardModule(args)

        initPresentationModel()
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View = inflater.inflate(R.layout.fragment_notes_card, container, false)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initRecycler()
    }

    private fun initPresentationModel() {
        presentation = ViewModelProviders.of(
            this,
            notesCardPresentationFactory
        )[NotesCardPresentationModel::class.java]

        presentation.modelLiveData.observe(this, Observer { viewModel: NoteCardViewModel ->
            update(viewModel)
        })
    }

    private fun initRecycler() {
        rvList.adapter = adapter
        rvList.layoutManager = LinearLayoutManager(context)
    }

    private fun getCompositeAdapter(): DiffUtilCompositeAdapter = DiffUtilCompositeAdapter.Builder()
        .add(TextAdapter())
        .build()

    private fun update(viewModel: NoteCardViewModel) {
        adapter.swapData(viewModel.items)
    }

    companion object {
        private const val ARGUMENTS_KEY = "ARGUMENTS"

        fun createArgs(args: NoteCardArguments): Bundle = Bundle(1).apply {
            putParcelable(ARGUMENTS_KEY, args)
        }
    }
}

@Parcelize
class NoteCardArguments(
    val noteId: String
) : Parcelable


class NoteCardViewModel(
    val items: List<IComparableItem>
)

