package com.example.davit_zakaryan.mvvmapp.data.db;


import com.example.davit_zakaryan.mvvmapp.data.db.model.Element;

import java.util.List;

import io.reactivex.Flowable;
import io.reactivex.Single;

public interface DbHelper {

	Flowable<List<Element>> findAll();

	//Flowable<List<Element>> findById();

	//Flowable<List<Element>> findByName();

	Single<Long> insertElement(Element element);

	Single<Boolean> insertAll(List<Element> elementLIst);

	Single<Boolean> updateElement(Element element);

	Single<Boolean> deleteElement(Element element);
}
