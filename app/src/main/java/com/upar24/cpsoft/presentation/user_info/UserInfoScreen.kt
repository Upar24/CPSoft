package com.upar24.cpsoft.presentation.user_info

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.SnackbarDefaults.backgroundColor
import androidx.compose.material.Text
import androidx.compose.material.contentColorFor
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.ramcosta.composedestinations.annotation.Destination
import com.upar24.cpsoft.data.remote.dto.UserDto
import com.upar24.cpsoft.domain.model.User
import com.upar24.cpsoft.presentation.user_listings.UserDetailItem

@Composable
@Destination
fun UserInfoScreen() {
    UserDetailItem()
}

