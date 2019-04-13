package com.blackstone.domain.entity

data class Order (
        val itemList: List<ItemOrder>,
        val townHall: TownHall
)