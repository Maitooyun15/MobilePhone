package com.example.mobilephone.model



import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize

@Parcelize
data class MobileImageModel(

    @SerializedName("id")
    val id: Int,

    @SerializedName("mobile_id")
    val mobileID: Int,

    @SerializedName("url")
    val url: String
) : Parcelable