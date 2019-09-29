package com.example.mobilephone.presenter

import com.example.mobilephone.model.MobileModel
import com.example.mobilephone.model.ModelPreferences
import com.example.mobilephone.service.MobilePhoneManager.Companion.service
import com.example.mobilephone.view.contract.MobileInterface
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MobileListPresenter(val view: MobileInterface, shareFav: ModelPreferences) {

    var list: List<MobileModel> = listOf()
    var addFav: ArrayList<MobileModel> = shareFav.getObject("model")

    fun addData(mobileModel: List<MobileModel>) {
        list = mobileModel
    }

    fun getMobileApi() {
        service.getMobileList().enqueue(object : Callback<List<MobileModel>> {
            override fun onFailure(call: Call<List<MobileModel>>, t: Throwable) {
                println("Failed")
            }

            override fun onResponse(call: Call<List<MobileModel>>, response: Response<List<MobileModel>>) {
                response.body()?.apply {
                    if (this.isNotEmpty()) {
                        for (i in this) {
                            for (j in addFav) {
                                if (i.id == j.id) {
                                    i.checked = true
                                }
                            }
                        }
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
