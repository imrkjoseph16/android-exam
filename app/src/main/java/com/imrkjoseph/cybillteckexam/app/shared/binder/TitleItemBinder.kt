package com.imrkjoseph.cybillteckexam.app.shared.binder

import android.view.LayoutInflater
import android.view.ViewGroup
import com.imrkjoseph.cybillteckexam.app.component.RecyclerViewHolder
import com.imrkjoseph.cybillteckexam.app.shared.dto.TitleItemViewDto
import com.imrkjoseph.cybillteckexam.databinding.ItemSharedTitleBinding

fun setupTitleItemBinder() = object : RecyclerViewHolder<ItemSharedTitleBinding, TitleItemViewDto> {
    override fun bind(binder: ItemSharedTitleBinding, item: TitleItemViewDto) = with(binder) {
        dto = item
        executePendingBindings()
    }

    override fun inflate(parent: ViewGroup) = ItemSharedTitleBinding.inflate(
        LayoutInflater.from(parent.context), parent, false)
}