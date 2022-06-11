package com.upar24.cpsoft.presentation.user_listings

import com.upar24.cpsoft.domain.model.User

data class UserState(
    val users: List<User> = emptyList(),
    val isLoading: Boolean = false,
    val isRefreshing: Boolean = false,
    val searchQuery: String = ""
)
