package com.imrkjoseph.cybillteckexam.app.shared.binder

import android.view.LayoutInflater
import android.view.ViewGroup
import com.imrkjoseph.cybillteckexam.app.component.RecyclerViewHolder
import com.imrkjoseph.cybillteckexam.app.shared.dto.ListItemViewDto
import com.imrkjoseph.cybillteckexam.databinding.ItemSharedListBinding

data class PersonListItem(
    val id: Any? = null,
    val dto: ListItemViewDto
)

fun <T : Any>setupPersonListItemBinder(
    dtoRetriever: (T) -> ListItemViewDto,
    onItemClicked: (ListItemViewDto) -> Unit = {}
) = object : RecyclerViewHolder<ItemSharedListBinding, T> {

    override fun bind(binder: ItemSharedListBinding, item: T) {
        with(binder) {
            val itemDto = dtoRetriever(item)
            dto = itemDto
            root.setOnClickListener { onItemClicked.invoke(itemDto) }
            executePendingBindings()
        }
    }

    override fun inflate(parent: ViewGroup) = ItemSharedListBinding.inflate(
        LayoutInflater.from(parent.context), parent, false)
}