package com.example.fordemocleanarch.data.common.interceptor

import android.content.Context
import android.net.ConnectivityManager
import android.net.NetworkCapabilities
import android.os.Build
import com.example.fordemocleanarch.data.common.exception.NoInternetException
import okhttp3.Interceptor
import okhttp3.Response
import javax.inject.Inject

/**
 * @author : Mingaleev D
 * @data : 21.10.2023
 */

class NetworkInterceptor @Inject constructor(
    private val context: Context
) : Interceptor {

   override fun intercept(chain: Interceptor.Chain): Response {
      if (isConnected()) {
         throw NoInternetException()
      }
      val req = chain.request().newBuilder().build()
      return chain.proceed(req)
   }

   private fun isConnected(): Boolean {
      val connectivityManager = context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
      if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
         val nw = connectivityManager.activeNetwork ?: return false
         val actNw = connectivityManager.getNetworkCapabilities(nw) ?: return false
         return when {
            actNw.hasTransport(NetworkCapabilities.TRANSPORT_WIFI) -> true
            actNw.hasTransport(NetworkCapabilities.TRANSPORT_CELLULAR) -> true
            else -> false
         }
      } else {
         val nwInfo = connectivityManager.activeNetworkInfo ?: return false
         return nwInfo.isConnected

      }
   }
}