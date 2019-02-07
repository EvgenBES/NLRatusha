package com.example.fox.ratusha.data.db.model

import com.example.fox.ratusha.ui.entity.ItemOrder
import com.example.fox.ratusha.ui.entity.TownHall

/**
 * @author Evgeny Butov
 * @since 04.02.2019
 */

fun ItemForpost.transformToPresenter(): ItemOrder {
    return ItemOrder(id = itemId, itemName = name, urlImage = image, countStart = countStart, countFinish = countFinish)
}

fun ItemOctal.transformToPresenter(): ItemOrder {
    return ItemOrder(id = itemId, itemName = name, urlImage = image, countStart = countStart, countFinish = countFinish)
}

fun InfoTownHall.transformToPresenter(): TownHall {
    return TownHall(id = idTown, start = start, finish = finish, url = url)
}