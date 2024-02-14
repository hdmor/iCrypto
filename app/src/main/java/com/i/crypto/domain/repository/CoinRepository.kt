package com.i.crypto.domain.repository

import com.i.crypto.data.remote.dto.CoinDetailDto
import com.i.crypto.data.remote.dto.CoinDto

interface CoinRepository {

    suspend fun getCoins(): List<CoinDto>
    suspend fun getCoinById(coinId: String): CoinDetailDto
}