package com.example.fordemocleanarch.domain.common.base

/**
 * @author : Mingaleev D
 * @data : 21.10.2023
 */

sealed class BaseResult<out T : Any, out U : Any> {

   data class Success<T : Any>(val data: T) : BaseResult<T, Nothing>()
   data class Error<U : Any>(val message: Failure) : BaseResult<Nothing, U>()

}