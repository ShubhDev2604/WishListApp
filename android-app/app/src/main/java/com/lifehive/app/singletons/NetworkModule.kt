package com.lifehive.app.singletons

import com.lifehive.app.repository.AuthRepository
import com.lifehive.app.retrofit.AuthApi
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object NetworkModule {

    @Provides
    @Singleton
    fun provideAuthApi(): AuthApi {
        return RetrofitClient.authApi
    }

    @Provides
    @Singleton
    fun provideAuthRepository(
        authApi: AuthApi
    ): AuthRepository {
        return AuthRepository(authApi)
    }
}
