package com.imrkjoseph.cybillteckexam.persons.presentation.list

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.imrkjoseph.cybillteckexam.app.shared.extension.coRunCatching
import com.imrkjoseph.cybillteckexam.app.shared.local.domain.DatabaseUseCase
import com.imrkjoseph.cybillteckexam.app.util.NetworkUtil
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
    private val localUseCase: DatabaseUseCase,
    private val networkUtil: NetworkUtil
) : ViewModel() {

    private val _uiState = MutableStateFlow(value = PersonState())

    val uiState = _uiState.asStateFlow()

    init {
        getCachedPersonLists()
    }

    private fun getCachedPersonLists() {
        viewModelScope.launch(context = Dispatchers.IO) {
            val cachedList = localUseCase.getPersonList(page = _uiState.value.currentPage)
            // Check if the cached details is not empty,
            // it means the response is already saved in local database.
            if (cachedList.results.isNotEmpty() || networkUtil.isNetworkAvailable().not()) handlePersonListResult(result = cachedList)
            // Fetching from API.
            else getPersonList()
        }
    }

    private fun getPersonList() {
        viewModelScope.launch {
            _uiState.update { it.copy(loading = true) }

            coRunCatching {
                personUseCase.getPersonList(page = _uiState.value.currentPage)
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

    fun clearCachedData() {
        if (networkUtil.isNetworkAvailable()) {
            viewModelScope.launch(context = Dispatchers.IO) {
                coRunCatching {
                    localUseCase.clearCachedData()
                }.onSuccess {
                    // Reset the currentPage into 1 and set an emptyList,
                    // to cachedList since we cleared the cached data.
                    _uiState.update {
                        it.copy(
                            currentPage = 1,
                            cachedPersonList = mutableListOf()
                        )
                    }
                    getCachedPersonLists()
                }
            }
        } else {
            _uiState.update { it.copy(error = Throwable()) }
        }
    }

    private fun handlePersonListResult(result: PersonListResponse) {
        with(_uiState.value) {
            cachedPersonList.addAll(result.results)

            getUiItems(response = PersonListResponse(
                info = result.info,
                results = cachedPersonList
            ))
        }
    }

    fun executeListPagination() {
        _uiState.value.currentPage += 1
        getCachedPersonLists()
    }

    private fun getUiItems(response: PersonListResponse) {
        factory.createOverview(data = response).also { uiItems ->
            _uiState.update {
                it.copy(uiItems = uiItems)
            }
        }
    }

    fun getPersonDetails(id: String) = uiState.value.cachedPersonList.find { it.id.value == id }
}