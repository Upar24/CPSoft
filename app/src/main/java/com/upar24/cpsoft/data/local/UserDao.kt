package com.upar24.cpsoft.data.local

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query

@Dao
interface UserDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertUser(
        userEntities : List<UserEntity>
    )

    @Query("DELETE FROM userentity")
    suspend fun clearUsers()

    @Query(
        """
            SELECT *
            FROM userentity
            WHERE LOWER(name) LIKE '%' || LOWER(:query) || '%'
        """
    )
    suspend fun searchUser(query: String) : List<UserEntity>

    @Query("SELECT * FROM userentity WHERE city =:query")
    suspend fun allUserByCity(query: String) : List<UserEntity>
}