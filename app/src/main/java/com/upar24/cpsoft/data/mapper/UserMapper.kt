package com.upar24.cpsoft.data.mapper

import com.upar24.cpsoft.data.local.UserEntity
import com.upar24.cpsoft.data.remote.dto.UserDto
import com.upar24.cpsoft.domain.model.User

fun UserEntity.toUser() : User {
    return User(
        name = name,
        city = city,
        address = address,
        email = email,
        phoneNumber = phoneNumber
    )
}
fun User.toUserEntity() : UserEntity {
    return UserEntity(
        name = name,
        city = city,
        address = address,
        email = email,
        phoneNumber = phoneNumber
    )
}
fun UserDto.toUser(): User {
    return User(
        name = name,
        city = city,
        address = address,
        email = email,
        phoneNumber = phoneNumber
    )
}