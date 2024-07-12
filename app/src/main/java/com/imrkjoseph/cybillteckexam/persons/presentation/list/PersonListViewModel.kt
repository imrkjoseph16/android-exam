package com.imrkjoseph.cybillteckexam.persons.presentation.list

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.imrkjoseph.cybillteckexam.app.shared.extension.coRunCatching
import com.imrkjoseph.cybillteckexam.app.shared.local.domain.DatabaseUseCase
import com.imrkjoseph.cybillteckexam.persons.data.dto.PersonListResponse
import com.imrkjoseph.cybillteckexam.persons.domain.PersonUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class PersonListViewModel @Inject constructor(
    private val personUseCase: PersonUseCase,
    private val factory: PersonListFactory,
    private val localUseCase: DatabaseUseCase
) : ViewModel() {

    private var currentPage = 1

    private val _uiState = MutableStateFlow(value = PersonState())

    val uiState = _uiState.asStateFlow()

    init {
        getCachedPersonLists()
    }

    private fun getCachedPersonLists() {
        viewModelScope.launch(context = Dispatchers.IO) {
            val cachedList = localUseCase.getPersonList(page = currentPage)
            // Check if the cached details is not empty,
            // it means the response is already saved in local database.
            if (cachedList.results.isNotEmpty()) handlePersonListResult(result = cachedList)
            // Fetching from API.
            else getPersonList()
        }
    }

    private fun getPersonList() {
        viewModelScope.launch {
            _uiState.update { it.copy(loading = true) }

            coRunCatching {
                personUseCase.getPersonList(page = currentPage)
            }.onSuccess { response ->
                // Handle person list response.
                handlePersonListResult(result = response)

                // Saved the response from API,
                // to local database to cached the person list.
                savePersonListsToLocal(result = response)
            }.onFailure { error ->
                _uiState.update { it.copy(error = error) }
            }

            _uiState.update { it.copy(loading = false) }
        }
    }

    private fun savePersonListsToLocal(result: PersonListResponse?) {
        result?.let {
            viewModelScope.launch(context = Dispatchers.IO) {
                localUseCase.saveLocalPersonLists(response = result)
            }
        }
    }

    private fun handlePersonListResult(result: PersonListResponse) = getUiItems(response = result)

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