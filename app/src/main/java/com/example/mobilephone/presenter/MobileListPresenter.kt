package com.example.mobilephone.presenter

import com.example.mobilephone.model.MobileApiService
import com.example.mobilephone.model.MobileModel
import com.example.mobilephone.model.MobilePhoneManager
import com.example.mobilephone.ui.main.MobileInterface
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MobileListPresenter(val view: MobileInterface, private val service: MobileApiService) {

    fun getMobileApi() {
        // เป็นประเภทที่ต้อง callback
        MobilePhoneManager().createService().getMobileList().enqueue(object : Callback<List<MobileModel>> {
            // เช่นกรณีเน็ตหลุด
            override fun onFailure(call: Call<List<MobileModel>>, t: Throwable) {
                println("Failed :")
            }

            override fun onResponse(call: Call<List<MobileModel>>, response: Response<List<MobileModel>>) {
                response.body()?.apply {
                    if (this.isNotEmpty()) {

                        view.setMobile(this)
                    }
                }

            }

        })


    }

}