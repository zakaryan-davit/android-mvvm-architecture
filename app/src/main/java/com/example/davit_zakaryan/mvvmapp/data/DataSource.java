package com.example.davit_zakaryan.mvvmapp.data;

import com.example.davit_zakaryan.mvvmapp.data.db.DbHelperImpl;
import com.example.davit_zakaryan.mvvmapp.data.db.model.ElementEntity;
import com.example.davit_zakaryan.mvvmapp.data.model.Element;
import com.example.davit_zakaryan.mvvmapp.data.network.NetworkHelper;
import com.example.davit_zakaryan.mvvmapp.data.prefs.PreferencesHelperImpl;
import com.example.davit_zakaryan.mvvmapp.util.ModelDaoConverter;

import java.util.List;

import javax.inject.Inject;
import javax.inject.Singleton;

import io.reactivex.Observable;
import io.reactivex.Single;

@Singleton
public class DataSource {

	private NetworkHelper networkHelper;
	private DbHelperImpl dbHelper;
	private PreferencesHelperImpl preferencesHelper;

	@Inject
	public DataSource(NetworkHelper networkHelper, DbHelperImpl dbHelper, PreferencesHelperImpl preferencesHelper) {
		this.networkHelper = networkHelper;
		this.dbHelper = dbHelper;
		this.preferencesHelper = preferencesHelper;
	}

	public Single<List<Element>> getElementListSingle() {
		Single<List<Element>> getElementSingle;
		if (!preferencesHelper.isDatabaseLoaded()) {
			getElementSingle = networkHelper
					.getElements()
					.map(itemModelListResponse -> itemModelListResponse.data)
					.flattenAsObservable(itemModels -> itemModels)
					.map(ModelDaoConverter::convertToElement)
					.toList()
					.doOnSuccess(elements -> Observable.fromIterable(elements)
							.map(ModelDaoConverter::convertToElementEntity)
							.toList()
							.subscribe(this::insertAll));
		} else {
			getElementSingle = dbHelper
					.findAll()
					.flattenAsObservable(elementEntities -> elementEntities)
					.map(ModelDaoConverter::convertToElement)
					.toList();
		}
		return getElementSingle;
	}

	private void insertAll(List<ElementEntity> elementEntities) {
		if (!preferencesHelper.isDatabaseLoaded()) {
			dbHelper.insertAll(elementEntities)
					.doOnSuccess(aBoolean -> preferencesHelper.setDatabaseLoaded(aBoolean))
					.subscribe(aBoolean -> System.out.println("Database inserted " + aBoolean));
		}
	}

	public Single<Long> insertElement(Element element) {
		return dbHelper.insertElement(ModelDaoConverter.convertToElementEntity(element));
	}

	public Single<Element> insertElementNetwork(Element element) {
		return networkHelper
				.addElement(ModelDaoConverter.convertToItemModel(element))
				.map(itemModelObjectResponse -> ModelDaoConverter.convertToElement(itemModelObjectResponse.data));
	}
}
