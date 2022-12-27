package com.purewowstudio.bodystats.domain.base

import java.math.RoundingMode
import java.text.DecimalFormat

fun Double.withSingleDecimalPlace(): Double = this.roundOffDecimal(places = 1)
fun Double.withTwoDecimalPlaces(): Double = this.roundOffDecimal(places = 2)

private fun Double.roundOffDecimal(places: Int = 1): Double {
    val df = when (places) {
        1 -> DecimalFormat("#.#")
        2 -> DecimalFormat("#.##")
        else -> DecimalFormat("#.##")
    }
    df.roundingMode = RoundingMode.CEILING
    return df.format(this).toDouble()
}

fun Double.convertDecimalPartToInt(): Int = Integer.parseInt(this.toString().split(".")[1])
