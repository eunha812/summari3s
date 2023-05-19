package com.notgenuis.summari3s.model.remote.service

import com.google.gson.GsonBuilder
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.converter.scalars.ScalarsConverterFactory

object ChatGPTServiceImpl {
    private const val CHAT_GPT_API_URL = "https://api.openai.com"

    private var service: ChatGPTService? = null

    fun getService() : ChatGPTService {
        if(service == null) {
            val interceptor = HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY)

            val client = OkHttpClient.Builder().addInterceptor(interceptor).build()

            val retrofit: Retrofit =
                Retrofit.Builder().baseUrl(CHAT_GPT_API_URL).client(client)
                    .addConverterFactory(ScalarsConverterFactory.create())
                    .addConverterFactory(
                        GsonConverterFactory.create(
                            GsonBuilder().setLenient().create()
                        )
                    ).build()

            service = retrofit.create(ChatGPTService::class.java)
        }

        return service!!
    }

}