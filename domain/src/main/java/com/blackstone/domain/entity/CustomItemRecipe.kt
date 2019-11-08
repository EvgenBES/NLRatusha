package com.blackstone.domain.entity

/**
 * @author Evgeny Butov
 * @created 07.11.2019
 */
data class CustomItemRecipe (
    val id: Int,
    val image: String,
    val name: String,
    val price: Int = 0,
    val number: Double = 0.00,
    val type: String = ""
)