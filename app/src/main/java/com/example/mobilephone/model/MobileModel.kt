package com.example.mobilephone.model


import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize

@Parcelize
data class MobileModel(

    @SerializedName("id")
    val id: Int,

    @SerializedName("name")
    val name: String,

    @SerializedName("description")
    val description: String,

    @SerializedName("brand")
    val band: String,

    @SerializedName("thumbImageURL")
    val imageUrl: String,

    @SerializedName("rating")
    val rating: Double,

    @SerializedName("price")
    val price: Double ,

    val chacked: Boolean = false

) : Parcelable