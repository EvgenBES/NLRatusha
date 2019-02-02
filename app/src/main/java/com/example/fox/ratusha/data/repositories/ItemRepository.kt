package com.example.fox.ratusha.data.repositories

import com.example.fox.ratusha.data.repositories.model.ItemModele

import io.reactivex.Flowable

interface ItemRepository {
    //Get all users from bd
    fun getAll() : Flowable<List<ItemModele>>
}
