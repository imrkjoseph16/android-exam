package com.imrkjoseph.cybillteckexam.persons.details

import com.imrkjoseph.cybillteckexam.app.foundation.BaseFragment
import com.imrkjoseph.cybillteckexam.databinding.FragmentPersonDetailsBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class PersonDetailsFragment : BaseFragment<FragmentPersonDetailsBinding>(bindingInflater = FragmentPersonDetailsBinding::inflate) {

    override fun onViewCreated() {
        super.onViewCreated()
    }
}