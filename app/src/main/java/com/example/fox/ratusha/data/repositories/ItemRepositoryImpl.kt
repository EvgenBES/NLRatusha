package com.example.fox.ratusha.data.repositories

import com.example.fox.ratusha.data.db.ItemDataBase
import com.example.fox.ratusha.data.repositories.model.ItemModele
import io.reactivex.Flowable
import javax.inject.Inject

class ItemRepositoryImpl @Inject constructor(val dataBase: ItemDataBase): ItemRepository {

    override fun getAll(): Flowable<List<ItemModele>> {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }
}


