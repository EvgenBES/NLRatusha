package com.example.fox.ratusha.ui.entity

data class Order (
        val startOrder: String = " ",
        val finishOrder: String = " ",
        val listItem: List<ItemOrder>,
        val product: String = " ",
        val urlProduct: String = " "
)