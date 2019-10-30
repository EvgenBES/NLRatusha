package com.blackstone.domain.extension

import java.text.DecimalFormat

/**
 * @author Evgeny Butov
 * @created 06.04.2019
 */

fun Double.twoCharAfterDot(): String{
    val formatText: DecimalFormat = DecimalFormat("#.##")
    return formatText.format(this)
}