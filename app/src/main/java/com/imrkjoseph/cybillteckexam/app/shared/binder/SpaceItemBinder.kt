package com.imrkjoseph.cybillteckexam.app.shared.binder

import android.view.LayoutInflater
import android.view.ViewGroup
import com.imrkjoseph.cybillteckexam.app.component.RecyclerViewHolder
import com.imrkjoseph.cybillteckexam.app.shared.dto.SpaceItemViewDto
import com.imrkjoseph.cybillteckexam.databinding.ItemSharedSpaceListBinding

val SpaceItemViewDtoBinder = object :
    RecyclerViewHolder<ItemSharedSpaceListBinding, SpaceItemViewDto> {
    override fun bind(binder: ItemSharedSpaceListBinding, item: SpaceItemViewDto) {
        with(binder) {
            dto = item
            executePendingBindings()
        }
    }

    override fun inflate(parent: ViewGroup) = ItemSharedSpaceListBinding.inflate(
        LayoutInflater.from(parent.context), parent, false)
}