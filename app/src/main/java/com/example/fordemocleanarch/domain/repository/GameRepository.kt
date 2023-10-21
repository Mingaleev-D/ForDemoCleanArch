package com.example.fordemocleanarch.domain.repository

import com.example.fordemocleanarch.domain.common.base.BaseResult
import com.example.fordemocleanarch.domain.common.base.Failure
import com.example.fordemocleanarch.domain.model.Game
import kotlinx.coroutines.flow.Flow

/**
 * @author : Mingaleev D
 * @data : 21.10.2023
 */

interface GameRepository {

   suspend fun fetchGame(): Flow<BaseResult<List<Game>, Failure>>
}