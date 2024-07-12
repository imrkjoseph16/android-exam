package com.imrkjoseph.cybillteckexam.persons.presentation.details

import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import com.imrkjoseph.cybillteckexam.app.component.CustomRecyclerView
import com.imrkjoseph.cybillteckexam.app.foundation.BaseFragment
import com.imrkjoseph.cybillteckexam.app.shared.binder.SpaceItemViewDtoBinder
import com.imrkjoseph.cybillteckexam.app.shared.binder.setupAvatarItemBinder
import com.imrkjoseph.cybillteckexam.app.shared.binder.setupToolbarItemBinder
import com.imrkjoseph.cybillteckexam.app.shared.binder.setupTitleItemBinder
import com.imrkjoseph.cybillteckexam.databinding.FragmentPersonDetailsBinding
import com.imrkjoseph.cybillteckexam.persons.presentation.list.PersonState
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch

@AndroidEntryPoint
class PersonDetailsFragment : BaseFragment<FragmentPersonDetailsBinding>(bindingInflater = FragmentPersonDetailsBinding::inflate) {

    private val viewModel: PersonDetailsViewModel by viewModels()

    override fun onViewCreated() {
        super.onViewCreated()
        with(binding) {
            setupComponents()
            setupObserver()
        }
    }

    private fun FragmentPersonDetailsBinding.setupComponents() {
        detailsList.setupPersonDetails()
    }

    private fun CustomRecyclerView.setupPersonDetails() {
        addItemBindings(viewHolders = SpaceItemViewDtoBinder)
        addItemBindings(viewHolders = setupTitleItemBinder())
        addItemBindings(viewHolders = setupAvatarItemBinder())
        addItemBindings(viewHolders = setupToolbarItemBinder(
            onBackClicked = findNavController()::popBackStack
        ))
    }

    private fun setupObserver() {
        with(viewModel) {
            viewLifecycleOwner.lifecycleScope.launch {
                uiState.collectLatest {  state ->
                    state.updateUi()
                }
            }
        }
    }

    private fun PersonState.updateUi() {
        binding.detailsList.setItems(items = uiItems)
    }
}