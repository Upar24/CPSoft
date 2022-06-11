package com.upar24.cpsoft.data.remote.dto

import androidx.room.PrimaryKey

data class UserDto(
    val name: String,
    val city: String,
    val address: String,
    val email: String,
    val phoneNumber: String,
    @PrimaryKey val id: String? = null
)
