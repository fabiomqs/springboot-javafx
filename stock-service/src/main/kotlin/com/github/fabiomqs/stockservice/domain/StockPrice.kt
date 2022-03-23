package com.github.fabiomqs.stockservice.domain

import java.time.LocalDateTime

data class StockPrice (
        val symbol: String,
        val price: Double,
        val time: LocalDateTime
)