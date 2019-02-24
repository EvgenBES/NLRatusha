package com.example.fox.ratusha.data.db.entity

import com.example.fox.ratusha.ui.entity.ItemOrder
import com.example.fox.ratusha.ui.entity.Order
import com.example.fox.ratusha.ui.entity.TownHall

/**
 * @author Evgeny Butov
 * @created 04.02.2019
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

fun ItemOrder.transformToItemForpostDao(): ItemForpost {
    return ItemForpost(id = 0, itemId = id, name = itemName, image = urlImage, countStart = countStart, countFinish = countFinish)
}

fun ItemOrder.transformToItemOctalDao(): ItemOctal {
    return ItemOctal(id = 0, itemId = id, name = itemName, image = urlImage, countStart = countStart, countFinish = countFinish)
}

fun Order.transformToTownHallDao(): InfoTownHall {
    return InfoTownHall(idTown = townHall.id, start = townHall.start, finish = townHall.finish, url = townHall.url)
}