package com.example.davit_zakaryan.mvvmapp.data.db;


import com.example.davit_zakaryan.mvvmapp.data.db.model.Element;

import java.util.List;

import io.reactivex.Flowable;

public interface DbHelper {

	Flowable<List<Element>> findAll();

	//Flowable<List<Element>> findById();

	//Flowable<List<Element>> findByName();

	//Single<Boolean> insertElement(Element element);

	//Single<Boolean> updateElement(Element element);

	//Single<Boolean> deleteElement(Element element);
}
