package com.twenk11k.stocks.repository

import android.util.Log
import com.skydoves.sandwich.message
import com.skydoves.sandwich.onError
import com.skydoves.sandwich.onException
import com.skydoves.sandwich.suspendOnSuccess
import com.skydoves.whatif.whatIfNotNull
import com.twenk11k.stocks.model.HandshakeRequest
import com.twenk11k.stocks.network.StocksClient
import com.twenk11k.stocks.util.Utils.getDeviceId
import com.twenk11k.stocks.util.Utils.getDeviceModel
import com.twenk11k.stocks.util.Utils.getManifacturer
import com.twenk11k.stocks.util.Utils.getSystemVersion
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import javax.inject.Inject

class StocksRepository @Inject constructor(
    private val stocksClient: StocksClient
) {

    suspend fun requestHandshake() = flow {

        val deviceId = getDeviceId()
        val systemVersion = getSystemVersion()
            val platformName = "Android"
        val deviceModel = getDeviceModel()
        val manifacturer = getManifacturer()
        Log.d("StockRepo","deviceId: $deviceId , systemVersion: $systemVersion , platformName: $platformName , deviceModel: $deviceModel , manifacturer: $manifacturer")
        val handshakeRequest = HandshakeRequest(deviceId,systemVersion, platformName, deviceModel, manifacturer)
        val response = stocksClient.fetchHandshakeResponse(handshakeRequest)
        response.suspendOnSuccess {
            data.whatIfNotNull { response ->
                Log.d("StockRepo",response.toString())
                emit(response)
            }
        }.onError {
            Log.d("StockRepo","error: ${message()}")
        }.onException {
            Log.d("StockRepo","exception: ${message()}")
        }

    }.flowOn(Dispatchers.IO)

}