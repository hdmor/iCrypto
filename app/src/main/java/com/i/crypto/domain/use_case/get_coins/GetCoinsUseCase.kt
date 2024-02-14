package com.i.crypto.domain.use_case.get_coins

import com.i.crypto.common.Resource
import com.i.crypto.data.remote.dto.toCoin
import com.i.crypto.domain.model.Coin
import com.i.crypto.domain.repository.CoinRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import retrofit2.HttpException
import java.io.IOException
import javax.inject.Inject

class GetCoinsUseCase @Inject constructor(private val repository: CoinRepository) {

    operator fun invoke(): Flow<Resource<List<Coin>>> = flow {
        try {
            emit(Resource.Loading())
            val coins = repository.getCoins().map { it.toCoin() }
            emit(Resource.Success(coins))

        } catch (e: HttpException) {
            emit(Resource.Error(message = e.localizedMessage?: "An unexpected error occurred."))
        } catch (io: IOException) {
            emit(Resource.Error(message = "Couldn't reach server. Check your internet connection."))
        }
    }
}