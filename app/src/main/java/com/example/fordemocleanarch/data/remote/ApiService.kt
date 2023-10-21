package com.example.fordemocleanarch.data.remote

import com.example.fordemocleanarch.data.remote.model.GameResponseItem
import retrofit2.Response
import retrofit2.http.GET

/**
 * @author : Mingaleev D
 * @data : 21.10.2023
 */

interface ApiService {

   companion object{
      const val BASE_URL = "https://www.freetogame.com/api/"
   }

   @GET("games")
   suspend fun fetchGame(): Response<List<GameResponseItem>>
}