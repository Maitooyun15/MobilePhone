package com.example.mobilephone

import android.content.Context
import com.example.mobilephone.model.MobileModel
import com.google.gson.Gson
import com.google.gson.GsonBuilder
import com.google.gson.reflect.TypeToken


class ModelPreferences(c: Context?) {

    val preferences = c?.getSharedPreferences("MODEL_PREFERENCES", Context.MODE_PRIVATE)
    val editor = preferences?.edit()
    private val gson = GsonBuilder().create()
    private lateinit var a: ArrayList<MobileModel>

    fun putObject(key: String, y: ArrayList<MobileModel>) {
        var into = gson.toJson(y)
        editor?.putString(key, into)?.apply()
    }

    fun getObject(key: String): ArrayList<MobileModel> {
        var gson2 = Gson()
        var into = preferences?.getString(key, null)
        a = try {
            gson2.fromJson(into, object : TypeToken<ArrayList<MobileModel>>() {}.type)
        } catch (ex: RuntimeException) {
            arrayListOf()
        }
        return a
    }


//    fun removeObject(key: String) {
//        editor.remove(key).apply()
//    }
}