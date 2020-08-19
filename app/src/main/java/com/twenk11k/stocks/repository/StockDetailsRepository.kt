package com.twenk11k.stocks.repository

import android.util.Log
import com.skydoves.sandwich.message
import com.skydoves.sandwich.onError
import com.skydoves.sandwich.onException
import com.skydoves.sandwich.suspendOnSuccess
import com.skydoves.whatif.whatIfNotNull
import com.twenk11k.stocks.model.StockDetailsRequest
import com.twenk11k.stocks.network.StocksClient
import com.twenk11k.stocks.util.Utils
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import javax.inject.Inject

class StockDetailsRepository @Inject constructor(
    private val stocksClient: StocksClient
) {

    suspend fun retrieveStockDetailsResponse(
        id: Int,
        aesKey: String,
        aesIV: String,
        authorization: String
    ) = flow {
        val idResponse = Utils.encryptResponse(id.toString(), aesKey, aesIV)
        val stockDetailsRequest = StockDetailsRequest(idResponse)
        val stockDetailsResponse =
            stocksClient.fetchStockDetailsResponse(authorization, stockDetailsRequest)
        stockDetailsResponse.suspendOnSuccess {
            data.whatIfNotNull {
                it.symbol = Utils.decryptValue(it.symbol, aesKey, aesIV).trim()
                emit(it)
            }
        }.onError {
            Log.e(javaClass.simpleName, "errorStocksResponse: ${message()}")
        }.onException {
            Log.e(javaClass.simpleName, "exceptionStocksResponse: ${message()}")
        }
    }.flowOn(Dispatchers.IO)

}