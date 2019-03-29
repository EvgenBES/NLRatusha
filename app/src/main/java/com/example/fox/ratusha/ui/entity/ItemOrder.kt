package com.example.fox.ratusha.ui.entity

/**
 * @author Evgeny Butov
 * @created 03.02.2019
 */
data class ItemOrder(
        val id: Int,
        val name: String,
        val image: String,
        val countStart: Int,
        val countFinish: Int,
        val price: Int = 0,
        val reputation: Double = 0.0,
        val countItemRep: Int = 0
)