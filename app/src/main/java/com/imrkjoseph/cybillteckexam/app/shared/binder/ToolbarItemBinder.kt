package com.imrkjoseph.cybillteckexam.app.shared.binder

import android.view.LayoutInflater
import android.view.ViewGroup
import com.imrkjoseph.cybillteckexam.app.component.RecyclerViewHolder
import com.imrkjoseph.cybillteckexam.app.shared.dto.ToolbarItemViewDto
import com.imrkjoseph.cybillteckexam.databinding.ItemSharedToolbarBinding

fun setupToolbarItemBinder(onBackClicked: () -> Unit = {}) = object : RecyclerViewHolder<ItemSharedToolbarBinding, ToolbarItemViewDto> {
    override fun bind(binder: ItemSharedToolbarBinding, item: ToolbarItemViewDto) = with(binder) {
        dto = item
        arrowBack.setOnClickListener { onBackClicked.invoke() }
        executePendingBindings()
    }

    override fun inflate(parent: ViewGroup) = ItemSharedToolbarBinding.inflate(
        LayoutInflater.from(parent.context), parent, false)
}