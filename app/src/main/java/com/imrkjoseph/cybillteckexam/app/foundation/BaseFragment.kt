package com.imrkjoseph.cybillteckexam.app.foundation

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.viewbinding.ViewBinding

abstract class BaseFragment<VB: ViewBinding>(
    private val bindingInflater: (inflater: LayoutInflater) -> VB
) : Fragment() {

    private var _binding: VB? = null

    protected val binding get() = _binding!!

    protected open fun onCreated(savedInstanceState: Bundle?) = Unit

    protected open fun onViewCreated() = Unit

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        onCreated(savedInstanceState)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        onViewCreated()
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ) = initViewBinding(inflater)

    private fun initViewBinding(
        inflater: LayoutInflater
    ): View {
        _binding = bindingInflater.invoke(inflater)
        return binding.root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        // Set the viewBinding into "null" to avoid memory leaks,
        // because Fragments outlives their view,
        // after onDestroyView being called.
        _binding = null
    }
}