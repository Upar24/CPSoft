package com.upar24.cpsoft.presentation.user_listings

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.Button
import androidx.compose.material.MaterialTheme
import androidx.compose.material.OutlinedTextField
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.google.accompanist.swiperefresh.SwipeRefresh
import com.google.accompanist.swiperefresh.rememberSwipeRefreshState
import com.ramcosta.composedestinations.annotation.Destination
import com.ramcosta.composedestinations.navigation.DestinationsNavigator
import com.upar24.cpsoft.presentation.destinations.UserInfoScreenDestination
import com.upar24.cpsoft.presentation.destinations.UsersSortedByNameDestination
import com.upar24.cpsoft.presentation.destinations.UsersSubListScreenByCityDestination
import com.upar24.cpsoft.presentation.user_info.UserInfoScreen


@Composable
@Destination(start= true)
fun UserListingsScreen(
    navigator: DestinationsNavigator,
    viewModel: UserListingsViewModel = hiltViewModel()
){
    val swipeRefreshState = rememberSwipeRefreshState(isRefreshing = viewModel.state.isRefreshing)
    val state = viewModel.state

    Column(
        modifier = Modifier.fillMaxSize()
    ) {
        OutlinedTextField(
            value = state.searchQuery,
            onValueChange ={
                viewModel.onEvent(
                    UserEvent.OnSearchQueryChange(it)
                )
            },
            modifier= Modifier
                .padding(16.dp)
                .fillMaxWidth(),
            placeholder = {
                Text(text="Search...")
            },
            maxLines = 1,
            singleLine = true
        )
        Row(
            Modifier
                .fillMaxWidth()
                .padding(4.dp),
            Arrangement.SpaceAround,
            verticalAlignment = Alignment.CenterVertically
        ){
            Button(onClick = {
                navigator.navigate(UsersSortedByNameDestination)
            }) {
                Text(
                    text = "Sorted By Name",
                    style= MaterialTheme.typography.button
                )
            }
            CitySelection(navigator = navigator, filterList = true)
            Button(onClick = {
                navigator.navigate(UsersSortedByNameDestination)
            }) {
                Text(
                    text = "Add User",
                    style= MaterialTheme.typography.button
                )
            }

        }

        SwipeRefresh(
            state = swipeRefreshState,
            onRefresh = {
                viewModel.onEvent(UserEvent.Refresh)
            }) {
            LazyColumn(
                modifier = Modifier.fillMaxSize().padding(16.dp)
            ){
                items(state.users.size){i ->
                    val user = state.users[i]
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
}












