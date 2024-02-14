package com.i.crypto.domain.use_case.get_coin

import com.i.crypto.common.Resource
import com.i.crypto.data.remote.dto.toCoinDetail
import com.i.crypto.domain.model.CoinDetail
import com.i.crypto.domain.repository.CoinRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import retrofit2.HttpException
import java.io.IOException
import javax.inject.Inject

class GetCoinDetailUseCase @Inject constructor(private val repository: CoinRepository) {

    operator fun invoke(coinId: String): Flow<Resource<CoinDetail>> = flow {
        try {
            emit(Resource.Loading())
            val coin = repository.getCoinById(coinId).toCoinDetail()
            emit(Resource.Success(coin))

        } catch (e: HttpException) {
            emit(Resource.Error(message = e.localizedMessage ?: "An unexpected error occurred."))
        } catch (io: IOException) {
            emit(Resource.Error(message = "Couldn't reach server. Check your internet connection."))
        }
    }
}