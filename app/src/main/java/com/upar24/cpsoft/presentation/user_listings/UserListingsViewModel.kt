package com.upar24.cpsoft.presentation.user_listings

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.upar24.cpsoft.domain.repository.UserRepository
import com.upar24.cpsoft.util.Resource
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Job
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class UserListingsViewModel @Inject constructor(
    private val repository: UserRepository
) : ViewModel(){
    var state by mutableStateOf(UserState())
    private var searchJob: Job? = null

    fun onEvent(event: UserEvent){
        when(event){
            is UserEvent.Refresh ->{
                getUsers(fetchFromRemote = true)
            }
            is UserEvent.OnSearchQueryChange -> {
                state= state.copy(searchQuery = event.query)
                searchJob?.cancel()
                searchJob = viewModelScope.launch {
                    delay(500L)
                    getUsers()
                }
            }
        }
    }
    private fun getUsers(
        query: String = state.searchQuery.lowercase(),
        fetchFromRemote: Boolean = false
    ){
        viewModelScope.launch {
            repository
                .getUserListings(fetchFromRemote,query)
                .collect{result ->
                    when(result){
                        is Resource.Success -> {
                            result.data?.let {listings ->
                                state = state.copy(
                                    users= listings
                                )
                            }
                        }
                        is Resource.Error -> Unit
                        is Resource.Loading -> {
                            state = state.copy(isLoading = result.isLoading)
                        }
                    }
                }
        }
    }
}