package com.example.mobilephone.presenter

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
            view.setMobile(data.toList().sortedBy { it.price })
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