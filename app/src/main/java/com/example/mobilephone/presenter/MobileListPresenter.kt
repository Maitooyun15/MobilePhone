package com.example.mobilephone.presenter

import com.example.mobilephone.model.MobileModel
import com.example.mobilephone.service.MobileApiService
import com.example.mobilephone.view.MobileInterface
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MobileListPresenter(val view: MobileInterface, private val service: MobileApiService) {

    var list: List<MobileModel> = listOf()

    fun addData(model: List<MobileModel>) {
        list = model
    }

    fun getMobileApi() {
        service.getMobileList().enqueue(object : Callback<List<MobileModel>> {
            override fun onFailure(call: Call<List<MobileModel>>, t: Throwable) {
                println("Failed :")
            }
            override fun onResponse(call: Call<List<MobileModel>>, response: Response<List<MobileModel>>) {
                response.body()?.apply {
                    if (this.isNotEmpty()) {

                        addData(this)
                        view.setMobile(this)
                    }
                }

            }

        })
    }

    fun getMobileHighToLow() {

        view.setMobile(list.sortedByDescending { it.price })
    }

    fun getMobileSortLowToHigh() {
        view.setMobile(list.sortedBy { it.price })
    }

    fun getMobileSortRating() {
        view.setMobile(list.sortedByDescending { it.rating })
    }


}
