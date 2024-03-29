package com.example.mobilephone.view.contract

import com.example.mobilephone.model.MobileModel

interface MobileInterface {
    fun setMobile(mobileList: List<MobileModel>)
    fun showErrorMsg(msg: String)

    interface OnClickMobileList {
        fun onMobileDetailClick(mobile: MobileModel)
        fun onFavoriteClick(favorite: MobileModel)
        fun onRemoveHeart(remove: MobileModel)
    }
}