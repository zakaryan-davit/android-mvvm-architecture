package com.example.davit_zakaryan.mvvmapp.data.db;


import com.example.davit_zakaryan.mvvmapp.data.db.model.DaoMaster;
import com.example.davit_zakaryan.mvvmapp.data.db.model.DaoSession;
import com.example.davit_zakaryan.mvvmapp.data.db.model.Element;

import java.util.List;

import javax.inject.Inject;

import io.reactivex.Flowable;
import io.reactivex.Single;

public class DbHelperImpl implements DbHelper {

	private DaoSession daoSession;

	@Inject
	DbHelperImpl(DbOpenHelper dbOpenHelper) {
		daoSession = new DaoMaster(dbOpenHelper.getWritableDb()).newSession();
	}

	@Override
	public Flowable<List<Element>> findAll() {
		return Flowable.fromCallable(() -> daoSession.getElementDao().loadAll());
	}

	@Override
	public Single<Long> insertElement(Element element) {
		return Single.fromCallable(() -> daoSession.getElementDao().insert(element));
	}

	@Override
	public Single<Boolean> insertAll(List<Element> elements) {
		return Single.fromCallable(() -> {
			daoSession.getElementDao().insertInTx(elements);
			return true;
		});
	}

	@Override
	public Single<Boolean> updateElement(Element element) {
		return Single.fromCallable(() -> {
			daoSession.getElementDao().update(element);
			return true;
		});
	}

	@Override
	public Single<Boolean> deleteElement(Element element) {
		return Single.fromCallable(() -> {
			daoSession.getElementDao().deleteByKey(element.getId());
			return true;
		});
	}


}
