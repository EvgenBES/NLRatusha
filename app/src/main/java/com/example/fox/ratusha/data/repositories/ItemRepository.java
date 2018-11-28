package com.example.fox.ratusha.data.repositories;

import com.example.fox.ratusha.data.repositories.model.ItemModele;

import java.util.List;

import io.reactivex.Flowable;

public interface ItemRepository {
    //Get all users from bd
    Flowable<List<ItemModele>> getAll();
}
