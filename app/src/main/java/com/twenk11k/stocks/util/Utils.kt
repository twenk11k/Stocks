package com.twenk11k.stocks.util

import android.annotation.SuppressLint
import android.content.Context
import android.os.Build
import android.provider.Settings.Secure
import android.util.Base64
import com.twenk11k.stocks.App
import com.twenk11k.stocks.R
import java.security.spec.AlgorithmParameterSpec
import javax.crypto.Cipher
import javax.crypto.spec.IvParameterSpec
import javax.crypto.spec.SecretKeySpec

object Utils {

    @SuppressLint("HardwareIds")
    fun getDeviceId(): String {
        return Secure.getString(
            App.getContext().contentResolver,
            Secure.ANDROID_ID
        )
    }

    fun getSystemVersion(): String {
        return Build.VERSION.SDK_INT.toString()
    }

    fun getDeviceModel(): String {
        return Build.MODEL.toString()
    }

    fun getManufacturer(): String {
        return Build.MANUFACTURER.toString()
    }

    fun encryptResponse(value: String, aesKey: String, aesIv: String): String {
        try {
            val key = aesKey.toByteArray()
            val iv = aesIv.toByteArray()
            val cipher = Cipher.getInstance("AES/CBC/PKCS7Padding")
            val secretKeySpec = SecretKeySpec(Base64.decode(key, Base64.DEFAULT), "AES")
            val paramSpec: AlgorithmParameterSpec = IvParameterSpec(Base64.decode(iv, Base64.DEFAULT))
            cipher.init(Cipher.ENCRYPT_MODE, secretKeySpec, paramSpec)
            return Base64.encodeToString(
                cipher.doFinal(value.toByteArray()),
                Base64.DEFAULT
            )
        } catch (e: java.lang.Exception) {
            e.printStackTrace()
        }
        return ""
    }

    fun decryptValue(value: String, aesKey: String, aesIv: String): String {
        try {
            val key = aesKey.toByteArray()
            val iv = aesIv.toByteArray()
            val cipher = Cipher.getInstance("AES/CBC/PKCS7Padding")
            val secretKeySpec = SecretKeySpec(Base64.decode(key, Base64.DEFAULT), "AES")
            val paramSpec: AlgorithmParameterSpec = IvParameterSpec(Base64.decode(iv, Base64.DEFAULT))
            cipher.init(Cipher.DECRYPT_MODE, secretKeySpec, paramSpec)
            return String(cipher.doFinal(Base64.decode(value.toByteArray(), Base64.DEFAULT)))
        } catch (e: java.lang.Exception) {
            e.printStackTrace()
        }
        return ""
    }

    fun getPeriodName(context: Context, str: String): String {
        return when (str) {
            context.getString(R.string.hisse_ve_endeksler) -> "all"
            context.getString(R.string.yukselenler) -> "increasing"
            context.getString(R.string.dusenler) -> "decreasing"
            context.getString(R.string.hacme_gore_30) -> "volume30"
            context.getString(R.string.hacme_gore_50) -> "volume50"
            context.getString(R.string.hacme_gore_100) -> "volume100"
            else -> "all"
        }
    }

}