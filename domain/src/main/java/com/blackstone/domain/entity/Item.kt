package com.blackstone.domain.entity

data class Item(
        val id: Int,
        val name: String,
        val categoryID: Int,
        val image: String,
        val price: Int,
        val reputation: Double,
        val countItemRep: Int,
        val level: Int = 12,
        val skill: Int = 0,
        val weight: Int = 0
)