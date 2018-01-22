package com.example.davit_zakaryan.mvvmapp.data;

import android.databinding.BaseObservable;

import com.example.davit_zakaryan.mvvmapp.data.db.DbHelperImpl;
import com.example.davit_zakaryan.mvvmapp.data.db.model.ElementEntity;
import com.example.davit_zakaryan.mvvmapp.data.model.Element;
import com.example.davit_zakaryan.mvvmapp.data.network.NetworkHelper;
import com.example.davit_zakaryan.mvvmapp.data.prefs.PreferencesHelperImpl;
import com.example.davit_zakaryan.mvvmapp.di.DatabaseChangeObservable;
import com.example.davit_zakaryan.mvvmapp.util.ModelDaoConverter;

import java.util.List;

import javax.inject.Inject;
import javax.inject.Singleton;

import io.reactivex.Flowable;
import io.reactivex.Observable;
import io.reactivex.Single;
import io.reactivex.functions.Consumer;

@Singleton
public class DataSource {

	private NetworkHelper networkHelper;
	private DbHelperImpl dbHelper;
	private PreferencesHelperImpl preferencesHelper;
	public final BaseObservable databaseChangeObservable;

	@Inject
	public DataSource(NetworkHelper networkHelper, DbHelperImpl dbHelper,
	                  PreferencesHelperImpl preferencesHelper,
	                  @DatabaseChangeObservable BaseObservable baseObservable) {
		this.networkHelper = networkHelper;
		this.dbHelper = dbHelper;
		this.preferencesHelper = preferencesHelper;
		this.databaseChangeObservable = baseObservable;
		//elements = new ObservableArrayList<>();
	}

	public Flowable<List<Element>> getElementListSingle() {
		Flowable<List<Element>> getElementSingle = null;
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
							.subscribe(this::insertAll))
					.toFlowable();
		} else {
			getElementSingle = dbHelper
					.findAll()
					.flatMapIterable(list -> list)
					.map(ModelDaoConverter::convertToElement)
					.toList()
					.toFlowable();
		}
		return getElementSingle;
	}

	private void insertAll(List<ElementEntity> elementEntities) {
		if (!preferencesHelper.isDatabaseLoaded()) {
			dbHelper.insertAll(elementEntities)
					.doOnSuccess(new Consumer<Boolean>() {
						@Override
						public void accept(Boolean aBoolean) throws Exception {
							databaseChangeObservable.notifyChange();
							preferencesHelper.setDatabaseLoaded(aBoolean);
						}
					})
					.subscribe(aBoolean -> System.out.println("Database inserted " + aBoolean));
		}
	}

	public Single<Long> insertElement(Element element) {
		return dbHelper.insertElement(ModelDaoConverter.convertToElementEntity(element))
				.doOnSuccess(new Consumer<Long>() {
					@Override
					public void accept(Long aLong) throws Exception {
						databaseChangeObservable.notifyChange();
					}
				});
	}

	public Single<Element> insertElementNetwork(Element element) {
		return networkHelper
				.addElement(ModelDaoConverter.convertToItemModel(element))
				.map(itemModelObjectResponse -> ModelDaoConverter.convertToElement(itemModelObjectResponse.data));
	}
}
