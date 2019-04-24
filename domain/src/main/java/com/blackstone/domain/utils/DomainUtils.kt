package com.blackstone.domain.utils

import com.blackstone.domain.entity.ItemOrder

object DomainUtils {

    fun sortItemOrderTwoParam(list: List<ItemOrder>): List<ItemOrder> {
        return list.sortedWith(compareBy ( {it.countStart}, {it.countFinish}))
    }

    fun sortItemOrder(list: List<ItemOrder>): List<ItemOrder> {
        return list.sortedWith(compareBy {it.countFinish - it.countStart}).reversed()
    }
}