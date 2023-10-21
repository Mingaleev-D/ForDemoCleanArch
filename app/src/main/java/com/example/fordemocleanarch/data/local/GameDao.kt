package com.example.fordemocleanarch.data.local

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy.REPLACE
import androidx.room.Query
import com.example.fordemocleanarch.domain.model.Game

/**
 * @author : Mingaleev D
 * @data : 21.10.2023
 */

@Dao
interface GameDao {

   @Insert(onConflict = REPLACE)
   fun insert(game: Game)

   @Insert(onConflict = REPLACE)
   fun insertAll(games: List<Game>)

   @Query("SELECT * FROM Game")
   fun getGames(): List<Game>

   @Query("DELETE FROM Game")
   fun deleteAll()
}