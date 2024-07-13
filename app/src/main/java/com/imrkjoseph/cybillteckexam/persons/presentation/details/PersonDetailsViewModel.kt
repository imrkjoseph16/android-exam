package com.imrkjoseph.cybillteckexam.persons.presentation.details

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import com.imrkjoseph.cybillteckexam.persons.presentation.list.PersonState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import javax.inject.Inject

@HiltViewModel
class PersonDetailsViewModel @Inject constructor(
    savedStateHandle: SavedStateHandle,
    private val factory: PersonDetailsFactory
) : ViewModel() {

    private val navArgs = PersonDetailsFragmentArgs.fromSavedStateHandle(savedStateHandle)

    private val _uiState = MutableStateFlow(value = PersonState())

    val uiState = _uiState.asStateFlow()

    init {
        getUiItems()
    }

    private fun getUiItems() {
        factory.createOverview(details= navArgs.details).also { uiItems ->
            _uiState.update {
                it.copy(uiItems = uiItems)
            }
        }
    }
}