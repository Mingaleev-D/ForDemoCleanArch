package com.example.fordemocleanarch.data.local

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.fordemocleanarch.domain.model.Game

/**
 * @author : Mingaleev D
 * @data : 21.10.2023
 */

@Database(entities = [Game::class], version = 1)
abstract class GameDatabase : RoomDatabase() {
   abstract fun gameDao(): GameDao
}