package com.example.fordemocleanarch.data.repository

import com.example.fordemocleanarch.data.datasource.GameRemoteSource
import com.example.fordemocleanarch.data.local.GameDao
import com.example.fordemocleanarch.domain.common.base.BaseResult
import com.example.fordemocleanarch.domain.common.base.Failure
import com.example.fordemocleanarch.domain.model.Game
import com.example.fordemocleanarch.domain.repository.GameRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

/**
 * @author : Mingaleev D
 * @data : 21.10.2023
 */

class GameRepositoryImpl @Inject constructor(
    private val gameRemoteSource: GameRemoteSource,
    private val gameDao: GameDao
) : GameRepository {

   override suspend fun fetchGame(): Flow<BaseResult<List<Game>, Failure>> {
      return flow {

         val local = gameDao.getGames()

         emit(BaseResult.Success(local))

         if(local.isEmpty()) {
            val remote = gameRemoteSource.fetchGame()
            if(remote is BaseResult.Success) {
               gameDao.deleteAll()
               gameDao.insertAll(remote.data)
            }
            emit(remote)
         }

         val result = gameRemoteSource.fetchGame()
         emit(result)

      }
   }
}