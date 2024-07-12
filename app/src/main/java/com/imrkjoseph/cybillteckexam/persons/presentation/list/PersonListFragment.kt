package com.imrkjoseph.cybillteckexam.persons.presentation.list

import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import com.imrkjoseph.cybillteckexam.app.component.CustomRecyclerView
import com.imrkjoseph.cybillteckexam.app.foundation.BaseFragment
import com.imrkjoseph.cybillteckexam.app.shared.binder.PersonListItem
import com.imrkjoseph.cybillteckexam.app.shared.binder.SpaceItemViewDtoBinder
import com.imrkjoseph.cybillteckexam.app.shared.binder.setupPersonListItemBinder
import com.imrkjoseph.cybillteckexam.app.shared.binder.setupTitleItemBinder
import com.imrkjoseph.cybillteckexam.app.shared.extension.setVisible
import com.imrkjoseph.cybillteckexam.databinding.FragmentPersonListBinding
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch

@AndroidEntryPoint
class PersonListFragment : BaseFragment<FragmentPersonListBinding>(bindingInflater = FragmentPersonListBinding::inflate) {

    private val viewModel: PersonListViewModel by viewModels()

    override fun onViewCreated() {
        super.onViewCreated()
        with(binding) {
            setupComponents()
            setupObserver()
        }
    }

    private fun FragmentPersonListBinding.setupComponents() {
        personList.setupPersonList()
    }

    private fun CustomRecyclerView.setupPersonList() {
        addItemBindings(viewHolders = SpaceItemViewDtoBinder)
        addItemBindings(viewHolders = setupTitleItemBinder())
        addItemBindings(viewHolders = setupPersonListItemBinder(
            dtoRetriever = PersonListItem::dto,
            onItemClicked = { dto ->
                navigateToDetailScreen(personId = dto.itemId)
            }
        ))
    }

    private fun setupObserver() {
        with(viewModel) {
            viewLifecycleOwner.lifecycleScope.launch {
                uiState.collectLatest { state ->
                    state.updateUi()
                }
            }
        }
    }

    private fun PersonState.updateUi() {
        updateLoadingState(isShow = loading)
        binding.personList.setItems(items = uiItems)
    }

    private fun updateLoadingState(isShow: Boolean) = binding.widgetLoading.root.setVisible(canShow = isShow)

    private fun navigateToDetailScreen(personId: String) = findNavController().navigate(
        directions = PersonListFragmentDirections.actionToPersonDetailsScreen(
            details = viewModel.getPersonDetails(id = personId)
        )
    )
}