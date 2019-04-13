package com.blackstone.domain.entity

/**
 * @author Evgeny Butov
 * @created 10.03.2019
 */
data class ItemRecipeFull (
        val id: Int,
        val image: String = "res_empty",
        val name: String = "Empty",
        val price: Int = 0,
        val number: Double = 0.00,
        val type: String = "empty"
)