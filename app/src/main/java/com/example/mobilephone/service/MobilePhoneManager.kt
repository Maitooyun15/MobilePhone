package com.example.mobilephone.service

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class MobilePhoneManager {
    companion object {
        const val BASE_MOBILE_API = "https://scb-test-mobile.herokuapp.com/api/"
    }

    fun createService(): MobileApiService =
        Retrofit.Builder()
            .baseUrl(BASE_MOBILE_API)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .run { create(MobileApiService::class.java) }
}
