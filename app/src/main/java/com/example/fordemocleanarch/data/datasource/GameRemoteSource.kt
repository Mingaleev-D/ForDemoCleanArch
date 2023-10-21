package com.example.fordemocleanarch.data.datasource

import com.example.fordemocleanarch.data.common.exception.NoInternetException
import com.example.fordemocleanarch.data.remote.ApiService
import com.example.fordemocleanarch.domain.common.base.BaseResult
import com.example.fordemocleanarch.domain.common.base.Failure
import com.example.fordemocleanarch.domain.model.Game
import javax.inject.Inject

/**
 * @author : Mingaleev D
 * @data : 21.10.2023
 */

class GameRemoteSource @Inject constructor(
    private val apiService: ApiService
) {

   suspend fun fetchGame(): BaseResult<List<Game>, Failure> {
      return try {
         val response = apiService.fetchGame()
         if (response.isSuccessful) {
            println("result")
            val games = mutableListOf<Game>()
            response.body()?.forEach { game ->
               games.add(
                   Game(
                       id = game.id,
                       platform = game.platform,
                       releaseDate = game.releaseDate,
                       thumbnail = game.thumbnail,
                       title = game.title
                   )
               )
            }
            return BaseResult.Success(games)
         } else {
            BaseResult.Error(Failure(response.code(), response.message()))
         }
      } catch (ex: NoInternetException) {
         println("NoInternetException")

         return BaseResult.Error(Failure(0, ex.message))

      } catch (ex: Exception) {
         println("Exception")
         return BaseResult.Error(Failure(-1, ex.message.toString()))
      }
   }
}