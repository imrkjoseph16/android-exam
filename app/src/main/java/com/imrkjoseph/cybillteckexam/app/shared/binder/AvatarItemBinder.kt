package com.imrkjoseph.cybillteckexam.app.shared.binder

import android.view.LayoutInflater
import android.view.ViewGroup
import com.imrkjoseph.cybillteckexam.app.component.RecyclerViewHolder
import com.imrkjoseph.cybillteckexam.app.shared.dto.AvatarItemViewDto
import com.imrkjoseph.cybillteckexam.databinding.ItemSharedAvatarBinding

fun setupAvatarItemBinder() = object : RecyclerViewHolder<ItemSharedAvatarBinding, AvatarItemViewDto> {
    override fun bind(binder: ItemSharedAvatarBinding, item: AvatarItemViewDto) = with(binder) {
        dto = item
        executePendingBindings()
    }

    override fun inflate(parent: ViewGroup) = ItemSharedAvatarBinding.inflate(
        LayoutInflater.from(parent.context), parent, false)
}