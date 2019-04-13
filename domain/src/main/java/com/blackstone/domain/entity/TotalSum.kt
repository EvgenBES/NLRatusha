package com.blackstone.domain.entity

data class TotalSum (
        val total: String = "0",
        val paid: String = "0",
        val paidPercent: String = "0",
        val remainder: String = "0",
        val remainderPercent: String = "0"
)