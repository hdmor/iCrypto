package com.i.crypto.data.remote.dto

import com.google.gson.annotations.SerializedName
import com.i.crypto.domain.model.Coin

data class CoinDto(
    val id: String,
    @SerializedName("is_active")
    val isActive: Boolean,
    @SerializedName("is_new")
    val isNew: Boolean,
    val name: String,
    val rank: Int,
    val symbol: String,
    val type: String
)

fun CoinDto.toCoin(): Coin =
    Coin(id = id, isActive = isActive, name = name, rank = rank, symbol = symbol)