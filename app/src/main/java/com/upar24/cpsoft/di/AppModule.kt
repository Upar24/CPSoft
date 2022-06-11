package com.upar24.cpsoft.di

import android.app.Application
import androidx.room.Room
import com.upar24.cpsoft.data.local.UserDatabase
import com.upar24.cpsoft.data.remote.UserApi
import com.upar24.cpsoft.util.Consts.BASE_URL
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import retrofit2.create
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {
    @Provides
    @Singleton
    fun provideUserApi():UserApi{
        return Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(MoshiConverterFactory.create())
            .build()
            .create()
    }
    @Provides
    @Singleton
    fun provideUserDatabase(app: Application):UserDatabase{
        return Room.databaseBuilder(
            app,
            UserDatabase::class.java,
            "userdb.db"
        ).build()
    }
}