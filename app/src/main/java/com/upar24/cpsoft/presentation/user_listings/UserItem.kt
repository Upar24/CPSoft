package com.upar24.cpsoft.presentation.user_listings

import androidx.compose.foundation.layout.Row
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import com.upar24.cpsoft.domain.model.User


@Composable
fun UserItem(
    user: User,
    modifier: Modifier = Modifier
){
    Row(
        modifier = modifier,
        verticalAlignment=Alignment.CenterVertically
    ){
        Text(
            text = user.name
        )
        Text(
            text = user.city
        )
    }
}
