package com.upar24.cpsoft.data.repository

import com.upar24.cpsoft.data.local.UserDatabase
import com.upar24.cpsoft.data.mapper.toUser
import com.upar24.cpsoft.data.mapper.toUserEntity
import com.upar24.cpsoft.data.remote.UserApi
import com.upar24.cpsoft.domain.model.User
import com.upar24.cpsoft.domain.repository.UserRepository
import com.upar24.cpsoft.util.Resource
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import retrofit2.HttpException
import java.io.IOException
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class UserRepositoryImpl @Inject constructor(
    private val api: UserApi,
    private val db: UserDatabase
): UserRepository {
    private val dao= db.dao
    override suspend fun getUserListings(
        fetchFromRemote: Boolean,
        query: String
    ): Flow<Resource<List<User>>> {
        return flow{
            emit(Resource.Loading(true))
            val localListings= dao.searchUser(query)
            emit(Resource.Success(data=localListings.map{it.toUser()}))
            val isDbEmpty = localListings.isEmpty() && query.isBlank()
            val shouldJustLoadFromCache = !isDbEmpty && !fetchFromRemote
            if(shouldJustLoadFromCache){
                emit(Resource.Loading(false))
                return@flow
            }
            val remoteListings = try {
                val response = api.getListings()
                response.body()?.map { it.toUser() }
            }catch (e: IOException){
                e.printStackTrace()
                emit(Resource.Error("Couldnt load data"))
                null
            }catch (e: HttpException){
                e.printStackTrace()
                emit(Resource.Error("Couldnt load data"))
                null
            }
            remoteListings?.let { listings ->
                dao.clearUsers()
                dao.insertUser(listings.map { it.toUserEntity() })
                emit(Resource.Success(
                    data = dao
                        .searchUser("")
                        .map { it.toUser() }
                )
                )
                emit(Resource.Loading(false))
            }
        }
    }
}
























