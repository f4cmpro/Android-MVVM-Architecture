package com.example.testformobiledeveloper.data.retrofit

import android.os.Build
import com.example.testformobiledeveloper.BuildConfig
import com.jakewharton.retrofit2.adapter.kotlin.coroutines.CoroutineCallAdapterFactory
import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.ResponseBody.Companion.toResponseBody
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import java.util.*
import java.util.concurrent.TimeUnit
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class ApiManager @Inject constructor() {
    private lateinit var retrofit: Retrofit

    companion object {
        private const val authorizationName = "Authorization"
        private const val authorizationValuePrefix = "OAuth2"
    }

    /***
     * okhttp
     */
    private val httpBuilder: OkHttpClient.Builder get() {

        return OkHttpClient.Builder()
            .addInterceptor(Interceptor { chain ->
                val original = chain.request()

                //common parameter
                val url = original.url.newBuilder()
//                    .addQueryParameter("os_type", "android")
//                    .addQueryParameter("os_version", "${Build.MODEL} ${Build.VERSION.RELEASE}")
//                    .addQueryParameter("app_version", BuildConfig.VERSION_NAME)
                    .build()

                //header
                val requestBuilder = original.newBuilder()
                    .header("Content-Type", "application/json")
                    .method(original.method, original.body)
                    .url(url)

                val response = chain.proceed(requestBuilder.build())
                val responseBody = response.body
                if (response.code != 200 || responseBody == null) {
                    return@Interceptor response
                }

                return@Interceptor response.newBuilder().body(responseBody.string().toResponseBody(responseBody.contentType())).build()
            })
            .readTimeout(30, TimeUnit.SECONDS)
    }

    /**
     * retrofit
     */
    fun <S> create(serviceClass: Class<S>): S{

        val moshi = Moshi.Builder()
            .add(KotlinJsonAdapterFactory())
            .build()

        retrofit = Retrofit.Builder()
            .baseUrl(BuildConfig.API_URL)
            .addConverterFactory(MoshiConverterFactory.create(moshi))
            .client(httpBuilder.build())
            .addCallAdapterFactory(CoroutineCallAdapterFactory())
            .build()

        return retrofit.create(serviceClass)
    }
}