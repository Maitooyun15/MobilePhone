package com.example.mobilephone

import android.content.Context
import android.util.Log
import com.google.gson.GsonBuilder

class ModelPreferences(c: Context) {

    val preferences = c.getSharedPreferences("MODEL_PREFERENCES", Context.MODE_PRIVATE)
    val editor = preferences.edit()
    private val gson = GsonBuilder().create()

    fun <T> putObject(key: String, y: T) {
        var into = gson.toJson(y)
        editor.putString(key, into).apply()

    }

    fun <T> getObject(key: String, c: Class<T>): T? {
        val value = preferences.getString(key, null)
        if (value != null) {
            return gson.fromJson(value, c)
        } else {
            //No JSON String with this key was found which means key is invalid or object was not saved.
            throw IllegalArgumentException("No object with key: $key was saved")
        }
    }


    fun removeObject(key: String) {
        editor.remove(key).apply()
    }
}