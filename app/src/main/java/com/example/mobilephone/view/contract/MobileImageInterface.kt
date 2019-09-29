package com.example.mobilephone.view.contract

import com.example.mobilephone.model.MobileImageModel


interface MobileImageInterface {
    fun setImageMobile(imageModel: List<MobileImageModel>)
    fun showErrorMsg(msg: String)
}