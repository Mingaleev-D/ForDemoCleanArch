package com.example.fordemocleanarch.domain.usecase

import com.example.fordemocleanarch.domain.common.base.BaseResult
import com.example.fordemocleanarch.domain.common.base.Failure
import com.example.fordemocleanarch.domain.model.Game
import com.example.fordemocleanarch.domain.repository.GameRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

/**
 * @author : Mingaleev D
 * @data : 21.10.2023
 */

class GetGameUseCase @Inject constructor(
    private val gameRepository: GameRepository
) {

   suspend fun invoke(): Flow<BaseResult<List<Game>, Failure>> {
      return gameRepository.fetchGame()
   }
}