package com.example.fox.ratusha.ui.entity

/**
 * @author Evgeny Butov
 * @created 10.03.2019
 */
data class ItemRecipe (
        val id: Int,
        val image: String = "ic_res_empty",
        val name: String = "Empty",
        val price: Int = 0,
        val number: Double = 0.00
)