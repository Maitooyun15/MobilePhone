package com.example.mobilephone.model

import android.content.Context
import android.util.Log
import com.google.gson.GsonBuilder
import com.google.gson.reflect.TypeToken


class ModelPreferences(c: Context?) {

    val preferences = c?.getSharedPreferences("MODEL_PREFERENCES", Context.MODE_PRIVATE)
    val editor = preferences?.edit()
    val gson = GsonBuilder().create()
    private lateinit var mobileList: ArrayList<MobileModel>


    fun putObject(key: String, mobileModel: ArrayList<MobileModel>) {
        var into = gson.toJson(mobileModel)
        Log.e("test", "เซฟไฟล์" + mobileModel.map { it.id }.toString())
        editor?.putString(key, into)?.apply()
        println("List" + mobileModel)
    }

    fun getObject(key: String): ArrayList<MobileModel> {
        var into = preferences?.getString(key, null)
        mobileList = try {
            gson.fromJson(into, object : TypeToken<ArrayList<MobileModel>>() {}.type)
        } catch (ex: RuntimeException) {
            arrayListOf()
        }
        return mobileList
    }
}
