package com.cft.randomuser.viewmodel

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.cft.randomuser.models.UserUiModel
import com.cft.randomuser.repositories.PreferencesRepository
import com.cft.randomuser.repositories.UserRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class UserListViewModel(
    private val repository: UserRepository,
    private val preferences: PreferencesRepository,
) : ViewModel() {

    private val _uiState = MutableStateFlow(UserListUiState(seed = preferences.getSeed()))
    val uiState: StateFlow<UserListUiState> = _uiState.asStateFlow()

    init {
        getUsers(10)
    }

    fun clearSeed() {
        _uiState.update { it.copy(seed = null) }
    }

    fun getUsers(count: Int) {
        viewModelScope.launch {
            try {
                val response = repository.getUsers(count, _uiState.value.seed)

                _uiState.value.seed
                    .takeIf { it.isNullOrEmpty() }
                    .let {
                        response.info?.seed?.let { seed -> preferences.setSeed(seed) }
                    }

                _uiState.update { it ->
                    it.copy(
                        users = response.results
                            .map { user ->
                                UserUiModel(
                                    first = user.name?.first,
                                    last = user.name?.last,
                                    streetNumber = user.location?.street?.number,
                                    streetName = user.location?.street?.name,
                                    city = user.location?.city,
                                    state = user.location?.state,
                                    country = user.location?.country,
                                    postcode = user.location?.postcode,
                                    cell = user.cell,
                                    medium = user.picture?.medium,
                                )
                            },
                        seed = response.info?.seed,
                        page = it.page + 1,
                    )
                }
            } catch (e: Exception) {
                Log.e(null, e.message ?: "Все хуёво")
            }
        }

    }

    fun getNextUsers(count: Int) {
        viewModelScope.launch {
            try {
                val seed = requireNotNull(_uiState.value.seed)
                val response = repository.getUsersPage(_uiState.value.page + 1, count, seed)

                _uiState.update { it ->
                    it.copy(
                        users = requireNotNull(it.users) + response.results
                            .map { user ->
                                UserUiModel(
                                    first = user.name?.first,
                                    last = user.name?.last,
                                    streetNumber = user.location?.street?.number,
                                    streetName = user.location?.street?.name,
                                    city = user.location?.city,
                                    state = user.location?.state,
                                    country = user.location?.country,
                                    postcode = user.location?.postcode,
                                    cell = user.cell,
                                    medium = user.picture?.medium,
                                )
                            },
                        page = it.page + 1,
                    )
                }
            } catch (e: Exception) {
                Log.e(null, e.message ?: "Все уже хуёво")
            }
        }
    }

}