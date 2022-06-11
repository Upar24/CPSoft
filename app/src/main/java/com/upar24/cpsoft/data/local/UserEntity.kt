package com.upar24.cpsoft.data.local

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class UserEntity(
    val name: String,
    val city: String,
    val address: String,
    val email: String,
    val phoneNumber: String,
    @PrimaryKey val id: String? = null
)