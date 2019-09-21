package com.example.mobilephone.presenter

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.mobilephone.model.MobileModel

class FragmentViewModel : ViewModel() {

    val data = MutableLiveData<MobileModel>()

    fun setMsgCommunicator(model: MobileModel) {
        data.value = model
    }
}