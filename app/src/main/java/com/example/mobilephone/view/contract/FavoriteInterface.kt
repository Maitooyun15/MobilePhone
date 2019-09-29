package com.example.mobilephone.view.contract

import com.example.mobilephone.model.MobileModel

interface FavoriteInterface {

    fun setMobile(favoriteList: List<MobileModel>)
    fun showErrorMsg(msg: String)

    interface OnClickFavoriteList {
        fun onFavoriteDetailClick(favorite: MobileModel)
        fun onSwipeRemove(unFav: MobileModel)
    }
}