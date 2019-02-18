package com.example.fox.ratusha.ui.entity

/**
 * @author Evgeny Butov
 * @created 03.02.2019
 */
data class ItemOrder(
        val id: Int,
        val itemName: String,
        val urlImage: String,
        val countStart: Int,
        val countFinish: Int
)
