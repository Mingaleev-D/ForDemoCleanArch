package com.example.fordemocleanarch.data.common.di

import com.example.fordemocleanarch.data.datasource.GameRemoteSource
import com.example.fordemocleanarch.data.local.GameDao
import com.example.fordemocleanarch.data.local.GameDatabase
import com.example.fordemocleanarch.data.remote.ApiService
import com.example.fordemocleanarch.data.repository.GameRepositoryImpl
import com.example.fordemocleanarch.domain.repository.GameRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object GameModule {

   @Provides
   @Singleton
   fun provideGameApiService(retrofit: Retrofit): ApiService {
      return retrofit.create(ApiService::class.java)
   }

   @Provides
   @Singleton
   fun provideGameRemoteSource(api: ApiService): GameRemoteSource {
      return GameRemoteSource(api)
   }

   @Provides
   @Singleton
   fun provideGameRepository(gameRemoteSource: GameRemoteSource, gameDao:GameDao): GameRepository {
      return GameRepositoryImpl(gameRemoteSource, gameDao)
   }

   @Provides
   @Singleton
   fun provideGameDao(gameDatabase: GameDatabase): GameDao {
     return gameDatabase.gameDao()
   }
}