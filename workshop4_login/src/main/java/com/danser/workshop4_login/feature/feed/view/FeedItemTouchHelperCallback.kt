package com.danser.workshop4_login.feature.feed.view

import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.RecyclerView
import java.util.Collections.swap

class FeedItemTouchHelperCallback(
    private val adapter: NotesAdapter
) : ItemTouchHelper.SimpleCallback(
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
