package com.example.fordemocleanarch.data.common.exception

import okio.IOException

/**
 * @author : Mingaleev D
 * @data : 21.10.2023
 */

class NoInternetException : IOException() {

   override val message: String
      get() = "Please check your internet connection"
}