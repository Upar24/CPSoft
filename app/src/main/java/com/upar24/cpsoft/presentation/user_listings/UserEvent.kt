package com.upar24.cpsoft.presentation.user_listings

sealed class UserEvent{
    object Refresh: UserEvent()
    data class OnSearchQueryChange(val query: String): UserEvent()
}
