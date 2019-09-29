package com.example.mobilephone.presenter

import com.example.mobilephone.model.MobileImageModel
import com.example.mobilephone.service.MobileApiService
import com.example.mobilephone.view.contract.MobileImageInterface
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MobileImagePresenter(val view: MobileImageInterface, private val service: MobileApiService) {

    fun getIdMobile(id: Int) {

        service.getMobileById(id).enqueue(object : Callback<List<MobileImageModel>> {
            override fun onFailure(call: Call<List<MobileImageModel>>, t: Throwable) {
                println("Failed")
            }

            override fun onResponse(call: Call<List<MobileImageModel>>, response: Response<List<MobileImageModel>>) {
                response.body()?.apply {
                    if (this.isNotEmpty()) {
                        view.setImageMobile(this)
                    }
                }

            }

        })
    }
}
