package com.upar24.cpsoft.presentation.user_listings

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.ramcosta.composedestinations.annotation.Destination
import com.ramcosta.composedestinations.navigation.DestinationsNavigator
import com.upar24.cpsoft.domain.model.User
import com.upar24.cpsoft.presentation.destinations.UserInfoScreenDestination

@Composable
@Destination
fun UsersSubListScreenByCity(
    navigator: DestinationsNavigator,
    city: String,
    viewModel: UserListingsViewModel = hiltViewModel()
){
    val list = viewModel.state.users.toMutableList()
    Box(
        Modifier.fillMaxSize().padding(16.dp)
    ){
        LazyColumn(
            modifier = Modifier.fillMaxSize()
        ){
            items(list.size){i ->
                val user = list[i]
                if(user.city == city){
                    UserItem(
                        user = user,
                        modifier = Modifier
                            .fillMaxWidth()
                            .clickable {
                                navigator.navigate(
                                    UserInfoScreenDestination
                                )
                            }
                    )
                }
            }

        }
    }
}
@Composable
@Destination
fun UsersSortedByName(
    navigator: DestinationsNavigator,
    viewModel: UserListingsViewModel = hiltViewModel()
){
    val users = viewModel.state.users.sortedBy { it.name }
    Box(
        Modifier.fillMaxSize().padding(16.dp)
    ){
        LazyColumn(
            modifier = Modifier.fillMaxSize()
        ){
            users.forEach {  }
            items(users.size){i ->
                val user = users[i]
                UserItem(
                    user = user,
                    modifier = Modifier
                        .fillMaxWidth()
                        .clickable {
                            navigator.navigate(
                                UserInfoScreenDestination()
                            )
                        }
                )
            }
        }
    }
}