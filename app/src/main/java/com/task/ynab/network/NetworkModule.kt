package com.task.ynab.network

import com.jakewharton.retrofit2.adapter.kotlin.coroutines.CoroutineCallAdapterFactory
import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ApplicationComponent
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Converter
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(ApplicationComponent::class)
object NetworkModule {
   // const val BASE_URL = "http://gateway.marvel.com/v1/public/"
   const val BASE_URL = "https://api.youneedabudget.com/v1/"

    const val  GeneralToken="bearer f977819463bd508f142f0208e29028f47869c642ffb44a121c9bad91fb6a73c9"

    @Provides
    @Singleton
    fun provideLoggerInterceptor():HttpLoggingInterceptor{
        return HttpLoggingInterceptor()
            .setLevel(HttpLoggingInterceptor.Level.BODY)
    }

    @Provides
    @Singleton
    fun provideOKHttpClient(logger: HttpLoggingInterceptor):OkHttpClient{
        val okHttpClient = OkHttpClient().newBuilder()
        okHttpClient.addInterceptor(logger)
        return okHttpClient.build()
    }

    @Provides
    @Singleton
    fun provideConverterFactory():Converter.Factory{
        return MoshiConverterFactory.create(
            Moshi.Builder().add(KotlinJsonAdapterFactory())
                .build()
        )
    }

    @Provides
    @Singleton
    fun provideRetrofit(okHttpClient: OkHttpClient,converterFactory:Converter.Factory):Retrofit{
        return Retrofit.Builder()
            .addCallAdapterFactory(CoroutineCallAdapterFactory())
            .addConverterFactory(converterFactory)
            .baseUrl(BASE_URL)
            .client(okHttpClient)
            .build()
    }
    @Provides
    @Singleton
    fun provideApiService(retrofit: Retrofit): ApiServices {
        return retrofit.create(ApiServices::class.java)
    }
}