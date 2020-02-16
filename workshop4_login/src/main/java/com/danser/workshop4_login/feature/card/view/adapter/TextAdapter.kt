package com.danser.workshop4_login.feature.card.view.adapter

import com.danser.workshop4_login.R
import com.example.delegateadapter.delegate.KDelegateAdapter
import com.example.delegateadapter.delegate.diff.IComparableItem
import kotlinx.android.synthetic.main.item_text.*

class TextAdapter : KDelegateAdapter<TextItem>() {
    override fun getLayoutId(): Int = R.layout.item_text

    override fun isForViewType(items: MutableList<*>, position: Int): Boolean =
        items[position] is TextItem

    override fun onBind(item: TextItem, viewHolder: KViewHolder) = with(viewHolder) {
        tvText.text = item.text
    }
}


data class TextItem(val text: String) : IComparableItem {

    override fun id(): Any = TextItem::class.java

    override fun content(): Any = this
}
