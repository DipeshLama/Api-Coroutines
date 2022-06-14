package com.example.apicoroutines.utils.helper

import java.math.BigDecimal
import java.math.RoundingMode

object DecimalHelper {

    @JvmStatic
    fun getRoundedOffPriceRs(price: Int): String {
        val bd = BigDecimal(price)
        val value = bd.setScale(2, RoundingMode.FLOOR)
        return "$value"
    }
}