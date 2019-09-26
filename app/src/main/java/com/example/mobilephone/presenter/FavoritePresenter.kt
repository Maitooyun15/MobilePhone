package com.example.mobilephone.presenter

import android.util.Log
import com.example.mobilephone.ModelPreferences
import com.example.mobilephone.view.fragment.FavoriteInterface

class FavoritePresenter(val view: FavoriteInterface, share: ModelPreferences) {

    var data = share.getObject("model")

    fun getFavorite() {
        if (data.isNotEmpty()) {
            view.setMobile(data.toList())
        }
    }

    fun getSortLowToHigh() {
        if (data.isNotEmpty()) {
            view.setMobile(data.sortedBy { it.price })
            //   println( data.toList().sortedBy { it.price })
            Log.e("test", data.toString())
        }
    }

    fun getSortHighToLow() {
        if (data.isNotEmpty()) {
            view.setMobile(data.toList().sortedByDescending { it.price })
        }
    }

    fun getSortRating() {
        if (data.isNotEmpty()) {
            view.setMobile(data.toList().sortedByDescending { it.rating })
        }
    }

}