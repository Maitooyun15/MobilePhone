package com.example.mobilephone.presenter

import com.example.mobilephone.model.MobileModel
import com.example.mobilephone.model.ModelPreferences
import com.example.mobilephone.model.ModelPreferences.Companion.FAVORITE_KEY
import com.example.mobilephone.service.MobileApiService
import com.example.mobilephone.view.contract.MobileInterface
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MobileListPresenter(
    val view: MobileInterface,
    shareFav: ModelPreferences,
    private val service: MobileApiService
) {

    var list: List<MobileModel> = listOf()
    var addFav: ArrayList<MobileModel> = shareFav.readFavorite(FAVORITE_KEY)

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
                        for (mobileApi in this) {
                            for (addShare in addFav) {
                                if (mobileApi.id == addShare.id) {
                                    mobileApi.checked = true
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
