package com.example.davit_zakaryan.mvvmapp.data.db;


import com.example.davit_zakaryan.mvvmapp.data.db.model.DaoMaster;
import com.example.davit_zakaryan.mvvmapp.data.db.model.DaoSession;
import com.example.davit_zakaryan.mvvmapp.data.db.model.ElementEntity;
import com.example.davit_zakaryan.mvvmapp.data.db.model.ElementEntityDao;

import java.util.List;

import javax.inject.Inject;

import io.reactivex.Single;

public class DbHelperImpl implements DbHelper {

	private DaoSession daoSession;
	private ElementEntityDao entityDao;

	@Inject
	DbHelperImpl(DbOpenHelper dbOpenHelper) {
		daoSession = new DaoMaster(dbOpenHelper.getWritableDb()).newSession();
		entityDao = daoSession.getElementEntityDao();
	}

	@Override
	public Single<List<ElementEntity>> findAll() {
		return Single.fromCallable(() -> entityDao.loadAll());
	}

	@Override
	public Single<Long> insertElement(ElementEntity elementEntity) {
		return Single.fromCallable(() -> entityDao.insert(elementEntity));
	}

	@Override
	public Single<Boolean> insertAll(List<ElementEntity> elementEntities) {
		return Single.fromCallable(() -> {
			entityDao.insertInTx(elementEntities);
			return true;
		});
	}

	@Override
	public Single<Boolean> updateElement(ElementEntity elementEntity) {
		return Single.fromCallable(() -> {
			entityDao.update(elementEntity);
			return true;
		});
	}

	@Override
	public Single<Boolean> deleteElement(ElementEntity elementEntity) {
		return Single.fromCallable(() -> {
			entityDao.deleteByKey(elementEntity.getId());
			return true;
		});
	}


}
