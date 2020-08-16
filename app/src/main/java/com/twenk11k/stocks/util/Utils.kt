package com.twenk11k.stocks.util

import android.os.Build
import android.provider.Settings.Secure
import com.twenk11k.stocks.App
import java.security.spec.AlgorithmParameterSpec
import javax.crypto.Cipher
import javax.crypto.spec.IvParameterSpec
import javax.crypto.spec.SecretKeySpec


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

    fun getDeviceModel(): String {
        return Build.MODEL.toString()
    }

    private fun capitalize(s: String): String {
        if (s.isEmpty()) {
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
        return Build.MANUFACTURER.toString()
    }

    fun encryptResponse(value: String, aesKey: String, aesIv: String): String {
        try {
            val key = aesKey.toByteArray()
            val ivs = aesIv.toByteArray()
            val cipher = Cipher.getInstance("AES/CBC/PKCS7Padding")
            val secretKeySpec =
                SecretKeySpec(android.util.Base64.decode(key, android.util.Base64.DEFAULT), "AES")
            val paramSpec: AlgorithmParameterSpec =
                IvParameterSpec(android.util.Base64.decode(ivs, android.util.Base64.DEFAULT))
            cipher.init(Cipher.ENCRYPT_MODE, secretKeySpec, paramSpec)
            return android.util.Base64.encodeToString(
                cipher.doFinal(value.toByteArray()),
                android.util.Base64.DEFAULT
            )
        } catch (e: java.lang.Exception) {
            e.printStackTrace()
        }
        return ""
    }

}