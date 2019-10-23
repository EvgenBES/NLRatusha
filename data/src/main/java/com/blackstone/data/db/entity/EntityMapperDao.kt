package com.blackstone.data.db.entity

import com.blackstone.domain.entity.*

/**
 * @author Evgeny Butov
 * @created 04.02.2019
 */

fun InfoTownHall.transformToPresenter(): TownHall {
    return TownHall(id = idTown, start = start, finish = finish, url = url)
}

fun ItemOrder.transformToItemForpostDao(): ItemForpost {
    return ItemForpost(id = 0, itemId = id, name = name, image = image, countStart = countStart, countFinish = countFinish)
}

fun ItemOrder.transformToItemOctalDao(): ItemOctal {
    return ItemOctal(id = 0, itemId = id, name = name, image = image, countStart = countStart, countFinish = countFinish)
}

fun List<ItemOrder>.transformToItemOctalDao(): List<ItemOctal> {
    val list = mutableListOf<ItemOctal>()
    this.forEach { list.add(it.transformToItemOctalDao()) }
    return list
}

fun List<ItemOrder>.transformToItemForpostDao(): List<ItemForpost> {
    val list = mutableListOf<ItemForpost>()
    this.forEach { list.add(it.transformToItemForpostDao()) }
    return list
}

fun Order.transformToTownHallDao(): InfoTownHall {
    return InfoTownHall(idTown = townHall.id, start = townHall.start, finish = townHall.finish, url = townHall.url)
}

fun Category.transformToPresenter(): ItemCategory {
    return ItemCategory(id = id , name = name, image = image)
}

fun Items.transformToItemCategory(): ItemCategory {
    return ItemCategory(id = id , name = name, image = image)
}

fun Items.transformToPresenter(): Item {
    return Item(id = id , name = name, categoryID = categoryID, image = image, price = price, reputation = reputation, countItemRep = countItemRep)
}

fun ItemRecipe.transformToFull(): ItemRecipeFull{
    return ItemRecipeFull(id = id, image = image, name = name, price = price, number = number)
}

fun ConfigApp.transformToConfig(): Config{
    return Config(tpForpost = tpForpost, tpOctal = tpOctal, statusForpost = statusForpost, statusOctal = statusOctal)
}

fun Config.transformToConfigApp(): ConfigApp{
    return ConfigApp(0, tpForpost = tpForpost, tpOctal = tpOctal, statusForpost = statusForpost, statusOctal = statusOctal)
}

fun ItemOrderExtended.transformToPresenter(): ItemOrder {
    return ItemOrder(id = id, name = name, image = image, countStart = countStart, countFinish = countFinish, price = price, reputation = reputation, countItemRep = countItemRep)
}