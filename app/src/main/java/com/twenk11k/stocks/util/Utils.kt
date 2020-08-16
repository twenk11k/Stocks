package com.twenk11k.stocks.util

import android.os.Build
import android.provider.Settings.Secure
import com.twenk11k.stocks.App


object Utils {

    fun getDeviceId(): String {
        val androidId = Secure.getString(
            App.getContext().getContentResolver(),
            Secure.ANDROID_ID
        )
        return androidId
    }

    fun getSystemVersion(): String {
        return android.os.Build.VERSION.SDK_INT.toString()
    }

    fun getDeviceName(): String {
        val manufacturer = Build.MANUFACTURER
        val model = Build.MODEL
        return if (model.toLowerCase().startsWith(manufacturer.toLowerCase())) {
            capitalize(model)
        } else {
            capitalize(manufacturer).toString() + " " + model
        }
    }

    fun getDeviceModel(): String{
        return android.os.Build.MODEL.toString()
    }

    private fun capitalize(s: String): String {
        if (s == null || s.length == 0) {
            return ""
        }
        val first = s[0]
        return if (Character.isUpperCase(first)) {
            s
        } else {
            Character.toUpperCase(first).toString() + s.substring(1)
        }
    }

    fun getManifacturer(): String {
        return android.os.Build.MANUFACTURER.toString()
    }

}