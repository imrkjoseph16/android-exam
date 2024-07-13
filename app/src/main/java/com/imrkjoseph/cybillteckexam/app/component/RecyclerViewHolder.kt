package com.imrkjoseph.cybillteckexam.app.component

import android.view.ViewGroup
import androidx.viewbinding.ViewBinding

interface RecyclerViewHolder<T : ViewBinding, I : Any> {

    fun bind(binder: T, item: I)

    fun inflate(parent: ViewGroup): T
}