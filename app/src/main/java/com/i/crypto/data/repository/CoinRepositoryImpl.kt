package com.i.crypto.data.repository

import com.i.crypto.data.remote.CoinPaprikaApi
import com.i.crypto.data.remote.dto.CoinDetailDto
import com.i.crypto.data.remote.dto.CoinDto
import com.i.crypto.domain.repository.CoinRepository
import javax.inject.Inject

class CoinRepositoryImpl @Inject constructor(private val api: CoinPaprikaApi) : CoinRepository {

    override suspend fun getCoins(): List<CoinDto> = api.getCoins()
    override suspend fun getCoinById(coinId: String): CoinDetailDto = api.getCoinById(coinId)
}