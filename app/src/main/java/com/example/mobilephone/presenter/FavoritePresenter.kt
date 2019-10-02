package com.example.mobilephone.presenter

import com.example.mobilephone.model.ModelPreferences
import com.example.mobilephone.model.ModelPreferences.Companion.FAVORITE_KEY
import com.example.mobilephone.view.contract.FavoriteInterface

class FavoritePresenter(val view: FavoriteInterface, private val share: ModelPreferences) {

    val readFavorite
        get() = share.readFavorite(FAVORITE_KEY)

    fun getFavorite() {
        if (readFavorite.isNotEmpty()) {
            view.setMobile(readFavorite)
        }
    }

    fun getSortLowToHigh() {
        if (readFavorite.isNotEmpty()) {
            view.setMobile(readFavorite.sortedBy { it.price })
        }
    }

    fun getSortHighToLow() {
        if (readFavorite.isNotEmpty()) {
            view.setMobile(readFavorite.sortedByDescending { it.price })
        }
    }

    fun getSortRating() {
        if (readFavorite.isNotEmpty()) {
            view.setMobile(readFavorite.sortedByDescending { it.rating })
        }
    }
}