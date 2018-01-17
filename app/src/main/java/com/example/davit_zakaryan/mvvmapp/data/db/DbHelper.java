package com.example.davit_zakaryan.mvvmapp.data.db;


import com.example.davit_zakaryan.mvvmapp.data.db.model.ElementEntity;

import java.util.List;

import io.reactivex.Single;

public interface DbHelper {

	Single<List<ElementEntity>> findAll();

	//Flowable<List<ElementEntity>> findById();

	//Flowable<List<ElementEntity>> findByName();

	Single<Long> insertElement(ElementEntity elementEntity);

	Single<Boolean> insertAll(List<ElementEntity> elementEntityLIst);

	Single<Boolean> updateElement(ElementEntity elementEntity);

	Single<Boolean> deleteElement(ElementEntity elementEntity);
}
