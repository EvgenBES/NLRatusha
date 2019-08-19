package com.blackstone.domain.entity

/**
 * @author Evgeny Butov
 * @created 18.05.2019
 */
data class Config (
    val tpForpost: Boolean = false,
    val tpOctal: Boolean = false,
    val statusForpost: Boolean = false,
    val statusOctal: Boolean = false
)