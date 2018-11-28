package com.example.fox.ratusha.data.repositories;

import com.example.fox.ratusha.data.db.ItemDataBase;
import com.example.fox.ratusha.data.repositories.model.ItemModele;

import java.util.List;

import javax.inject.Inject;

import io.reactivex.Flowable;

public class ItemRepositoryImpl implements ItemRepository {

    private ItemDataBase dataBase;

    @Inject
    public ItemRepositoryImpl(ItemDataBase dataBase){
        this.dataBase = dataBase;
    }


    @Override
    public Flowable<List<ItemModele>> getAll() {
        return null;
    }
}
