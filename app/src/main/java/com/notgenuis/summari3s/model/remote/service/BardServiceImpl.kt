package com.notgenuis.summari3s.model.remote.service

import com.google.gson.GsonBuilder
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.converter.scalars.ScalarsConverterFactory

object BardServiceImpl {
    private const val BARD_API_URL = "https://api.bardapi.dev"

    private var service: BardService? = null

    fun getService() : BardService {
        if(service == null) {
            val interceptor = HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY)

            val client = OkHttpClient.Builder().addInterceptor(interceptor).build()

            val retrofit: Retrofit =
                Retrofit.Builder().baseUrl(BARD_API_URL).client(client)
                    .addConverterFactory(ScalarsConverterFactory.create())
                    .addConverterFactory(
                        GsonConverterFactory.create(
                            GsonBuilder().setLenient().create()
                        )
                    ).build()

            service = retrofit.create(BardService::class.java)
        }

        return service!!
    }
}