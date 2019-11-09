package com.blackstone.data.db.entity

import com.blackstone.domain.entity.*

/**
 * @author Evgeny Butov
 * @created 04.02.2019
 */

fun InfoTownHall.transformToTownHall(): TownHall {
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

fun Category.transformToItemCategory(): ItemCategory {
    return ItemCategory(id = id , name = name, image = image)
}

fun Items.transformToItemCategory(): ItemCategory {
    return ItemCategory(id = id , name = name, image = image)
}

fun Items.transformToItem(): Item {
    return Item(id = id , name = name, categoryID = categoryID, image = image, price = price, reputation = reputation, countItemRep = countItemRep)
}

fun ItemRecipe.transformToFull(): ItemRecipeFull {
    return ItemRecipeFull(id = id, image = image, name = name, price = price, number = number)
}

fun CustomItemRecipe.transformToAlchemyRecipe(): ItemRecipeFull {
    return ItemRecipeFull(id = id, image = image, name = name, price = price, number = number, type = type, typeRecipe = TypeRecipe.ALCHEMY)
}

fun ItemOrderExtended.transformToItemOrder(): ItemOrder {
    return ItemOrder(id = id, name = name, image = image, countStart = countStart, countFinish = countFinish, price = price, reputation = reputation, countItemRep = countItemRep)
}