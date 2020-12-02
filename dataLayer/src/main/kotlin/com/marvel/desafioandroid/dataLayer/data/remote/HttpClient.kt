package com.marvel.desafioandroid.dataLayer.data.remote

import android.app.Application
import com.squareup.moshi.Moshi
import com.squareup.moshi.adapters.Rfc3339DateJsonAdapter
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import okhttp3.Cache
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import java.util.*
import java.util.concurrent.TimeUnit

class HttpClient(private val application: Application) {
    private lateinit var okHttpClient: OkHttpClient

    private companion object {
        private const val CACHE_OF_10_MB: Long = 10 * 1024 * 1024
    }

    fun <T> create(restApiClass: Class<T>, baseUrl: String? = null, interceptor: Interceptor? = null): T {
        return Retrofit.Builder()
            .baseUrl(baseUrl ?: com.marvel.desafioandroid.dataLayer.BuildConfig.BASE_URL)
            .addConverterFactory(createMoshi())
            .client(createOkHttp(interceptor))
            .build()
            .create(restApiClass)
    }

        private fun createOkHttp(interceptor: Interceptor? = null): OkHttpClient {
        val logging = HttpLoggingInterceptor()
        logging.level = HttpLoggingInterceptor.Level.BODY
        val cache = Cache(application.cacheDir, CACHE_OF_10_MB)

        val okHttpBuilder = OkHttpClient.Builder()
            .connectTimeout(95L, TimeUnit.SECONDS)
            .readTimeout(95L, TimeUnit.SECONDS)
            .writeTimeout(95L, TimeUnit.SECONDS)
            .cache(cache)
            .addInterceptor(logging)

        okHttpClient = okHttpBuilder.build()

        return okHttpClient
    }

    private fun createMoshi(): MoshiConverterFactory {
        return MoshiConverterFactory.create(
            Moshi.Builder()
                .add(KotlinJsonAdapterFactory())
                .add(Date::class.java, Rfc3339DateJsonAdapter())
                .build()
        )
    }
}
