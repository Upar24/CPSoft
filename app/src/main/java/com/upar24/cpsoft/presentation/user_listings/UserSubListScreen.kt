package com.upar24.cpsoft.presentation.user_listings

import androidx.compose.foundation.layout.Column
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.hilt.navigation.compose.hiltViewModel
import com.ramcosta.composedestinations.annotation.Destination
import com.ramcosta.composedestinations.navigation.DestinationsNavigator
import com.upar24.cpsoft.domain.model.User

@Composable
@Destination
fun UsersSubListScreenByCity(
    city: String,
    viewModel: UserListingsViewModel = hiltViewModel()
){
    val list = viewModel.state.users.toMutableList()
    Column {
        list.forEach {
            if(it.city ==city){
                Text(text = city)
            }
        }
    }
}
@Composable
@Destination
fun UsersSortedByName(
    viewModel: UserListingsViewModel = hiltViewModel()
){
    val users = viewModel.state.users.sortedBy { it.name }
    Column{    users.forEach {
        Text(it.name)
    }

    }
}