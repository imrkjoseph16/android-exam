package com.imrkjoseph.cybillteckexam.persons.list

import com.imrkjoseph.cybillteckexam.app.foundation.BaseFragment
import com.imrkjoseph.cybillteckexam.databinding.FragmentPersonListBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class PersonListFragment : BaseFragment<FragmentPersonListBinding>(bindingInflater = FragmentPersonListBinding::inflate) {

    override fun onViewCreated() {
        super.onViewCreated()
    }
}