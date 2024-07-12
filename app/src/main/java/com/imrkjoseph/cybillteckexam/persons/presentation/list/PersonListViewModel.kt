package com.imrkjoseph.cybillteckexam.persons.presentation.list

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.imrkjoseph.cybillteckexam.app.shared.extension.coRunCatching
import com.imrkjoseph.cybillteckexam.persons.data.dto.PersonListResponse
import com.imrkjoseph.cybillteckexam.persons.domain.PersonUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class PersonListViewModel @Inject constructor(
    private val personUseCase: PersonUseCase,
    private val factory: PersonListFactory
) : ViewModel() {

    private var currentPage = 1

    private val _uiState = MutableStateFlow(value = PersonState())

    val uiState = _uiState.asStateFlow()

    init {
        getPersonList()
    }

    private fun getPersonList() {
        viewModelScope.launch {
            _uiState.update { it.copy(loading = true) }

            coRunCatching {
                personUseCase.getPersonList(page = currentPage)
            }.onSuccess { result ->
                // Handle person list response.
                getUiItems(response = result)
            }.onFailure { error ->
                _uiState.update { it.copy(error = error) }
            }

            _uiState.update { it.copy(loading = false) }
        }
    }

    private fun getUiItems(response: PersonListResponse) {
        factory.createOverview(data = response).also { uiItems ->
            _uiState.update {
                it.copy(
                    response = response,
                    uiItems = uiItems
                )
            }
        }
    }

    fun getPersonDetails(id: String) = uiState.value.response?.results?.find { it.id.value == id }
}