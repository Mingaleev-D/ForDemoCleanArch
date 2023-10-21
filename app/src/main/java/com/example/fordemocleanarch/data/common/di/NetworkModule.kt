package com.example.fordemocleanarch.data.common.di

import android.content.Context
import com.example.fordemocleanarch.data.common.interceptor.NetworkInterceptor
import com.example.fordemocleanarch.data.remote.ApiService
import com.example.fordemocleanarch.data.remote.ApiService.Companion.BASE_URL
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object NetworkModule {

   @Provides
   fun provideInterceptor(@ApplicationContext context: Context): NetworkInterceptor {
      return NetworkInterceptor(context)
   }

   /**
    *  @baseUrl https://www.freetogame.com/api/
    *  по хорошему нужно заменить #BuildConfig.BASE_URL
    */

   @Provides
   fun provideBaseUrl(): String {
      return BASE_URL
   }

   @Provides
   @Singleton
   fun provideLoggingInterceptor(): HttpLoggingInterceptor {
      return HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY)
   }

   @Provides
   @Singleton
   fun provideOkHttpClient(logger: HttpLoggingInterceptor): OkHttpClient {
      val okHttpClient = OkHttpClient.Builder()
      okHttpClient.addInterceptor(logger)
      okHttpClient.callTimeout(30, TimeUnit.SECONDS)
      okHttpClient.connectTimeout(30, TimeUnit.SECONDS)
      okHttpClient.writeTimeout(30, TimeUnit.SECONDS)
      okHttpClient.readTimeout(30, TimeUnit.SECONDS)
      val okHttp = okHttpClient.build()
      return okHttp
   }

   @Provides
   @Singleton
   fun provideConvectorFactory(): GsonConverterFactory {
      return GsonConverterFactory.create()
   }

   @Provides
   @Singleton
   fun provideRetrofit(
       baseUrl: String,
       gsonFactory: GsonConverterFactory,
       okHttpClient: OkHttpClient
   ): Retrofit {
      return Retrofit.Builder()
          .baseUrl(baseUrl)
          .addConverterFactory(gsonFactory)
          .client(okHttpClient)
          .build()
   }

}