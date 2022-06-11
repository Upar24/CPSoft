package com.upar24.cpsoft.domain.repository

import com.upar24.cpsoft.domain.model.User
import com.upar24.cpsoft.util.Resource
import kotlinx.coroutines.flow.Flow

interface UserRepository {
    suspend fun getUserListings(
        fetchFromRemote: Boolean,
        query: String
    ): Flow<Resource<List<User>>>
}