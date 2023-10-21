package com.example.fordemocleanarch.data.common.di

import android.content.Context
import androidx.room.Room
import com.example.fordemocleanarch.data.local.GameDatabase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object GameDatabase {

   @Provides
   @Singleton
   fun provideGameDatabase(@ApplicationContext context: Context): GameDatabase {
      return Room.databaseBuilder(
          context,
          GameDatabase::class.java,
          "game_database"
      ).allowMainThreadQueries().build()
   }
}