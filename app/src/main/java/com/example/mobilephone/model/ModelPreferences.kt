package com.example.mobilephone.model

import android.content.Context
import android.util.Log
import com.google.gson.GsonBuilder
import com.google.gson.reflect.TypeToken


class ModelPreferences(context: Context?) {

    companion object {
        const val FAVORITE_KEY = "model"
    }

    private val preferences = context?.getSharedPreferences("MODEL_PREFERENCES", Context.MODE_PRIVATE)
    private val editor = preferences?.edit()
    private val gson = GsonBuilder().create()
    private lateinit var mobileList: ArrayList<MobileModel>

    fun saveFavorite(key: String, mobileModel: ArrayList<MobileModel>) {
        val into = gson.toJson(mobileModel)
        editor?.putString(key, into)?.apply()
    }

    fun readFavorite(key: String): ArrayList<MobileModel> {
        val into = preferences?.getString(key, null)
        mobileList = try {
            gson.fromJson(into, object : TypeToken<ArrayList<MobileModel>>() {}.type)
        } catch (ex: RuntimeException) {
            arrayListOf()
        }
        return mobileList
    }
}
