package com.blackstone.domain.extension

import java.math.BigDecimal
import java.math.RoundingMode

/**
 * @author Evgeny Butov
 * @created 06.04.2019
 */

fun Double.twoCharAfterDot(): String{
    return BigDecimal(this).setScale(2, RoundingMode.UP).toString()
}

fun Double.zeroCharAfterDot(): String{
    return BigDecimal(this).setScale(0, RoundingMode.UP).toString()
}