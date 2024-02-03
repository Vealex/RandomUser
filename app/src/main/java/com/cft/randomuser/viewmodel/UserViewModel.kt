package com.cft.randomuser.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.cft.randomuser.errors.UserStatement
import com.cft.randomuser.mapper.UserUiModelMapper
import com.cft.randomuser.repositories.UserRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class UserViewModel(
    private val repository: UserRepository,
    private val mapper: UserUiModelMapper,
    id: Int,
    page: Int,
    seed: String,
) : ViewModel() {

    private val _uiState = MutableStateFlow(UserUiState())
    val uiState = _uiState.asStateFlow()

    init {
        getUser(id, page, seed)
    }

    fun getUser(id: Int, page: Int, seed: String) {
        viewModelScope.launch {
            try {
                _uiState.update {
                    it.copy(
                        statement = UserStatement.LOADING
                    )
                }

                val count = if (page == 1) {
                    UserListViewModel.INIT_LOAD
                } else {
                    UserListViewModel.NEXT_PAGE_LOAD
                }
                val response = repository.getUsersPage(
                    page,
                    count,
                    seed,
                )

                _uiState.update {
                    val user = response.results[id]
                    it.copy(
                        user = mapper.map(user),
                        statement = null
                    )
                }
            } catch (e: Exception) {
                _uiState.update {
                    it.copy(
                        statement = UserStatement.LOAD_ERROR
                    )
                }
            }
        }
    }

    fun errorHandled() {
        _uiState.update {
            it.copy(
                statement = null
            )
        }
    }

}