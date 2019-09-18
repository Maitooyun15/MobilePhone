package com.example.mobilephone.model

import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path

interface MobileApiService {
    @GET("mobiles/")
    fun getMobileList() : Call<List<MobileModel>>

    @GET("mobiles/{mobile_id}/images/")
    fun getMobileById(@Path("mobile_id") mobileId: Int?): Call<List<MobileImageModel>>
}