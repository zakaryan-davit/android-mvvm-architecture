package com.example.davit_zakaryan.mvvmapp.data.db;


import com.example.davit_zakaryan.mvvmapp.data.db.model.CardGameEntity;
import com.example.davit_zakaryan.mvvmapp.data.db.model.CardGameEntityDao;
import com.example.davit_zakaryan.mvvmapp.data.db.model.DaoSession;
import com.example.davit_zakaryan.mvvmapp.data.db.model.ElementEntity;
import com.example.davit_zakaryan.mvvmapp.data.db.model.ElementEntityDao;
import com.example.davit_zakaryan.mvvmapp.data.db.model.SnakeEntityDao;
import com.example.davit_zakaryan.mvvmapp.data.db.model.TaskEntityDao;

import java.util.List;

import javax.inject.Inject;

import io.reactivex.Flowable;
import io.reactivex.Single;

public class DbHelperImpl implements DbHelper {

	private ElementEntityDao entityDao;
	private CardGameEntityDao cardGameEntityDao;
	private SnakeEntityDao snakeEntityDao;
	private TaskEntityDao taskEntityDao;

	@Inject
	DbHelperImpl(DaoSession daoSession) {
		entityDao = daoSession.getElementEntityDao();
		cardGameEntityDao = daoSession.getCardGameEntityDao();
		snakeEntityDao = daoSession.getSnakeEntityDao();
		taskEntityDao = daoSession.getTaskEntityDao();
	}

	@Override
	public Flowable<List<ElementEntity>> findAll() {
		return Flowable.fromCallable(() -> entityDao.loadAll());
	}

	public Single<List<CardGameEntity>> findAllGames() {
		return Single.fromCallable(() -> cardGameEntityDao.loadAll());
	}

	@Override
	public Single<Long> insertElement(ElementEntity elementEntity) {
		//return Single.fromCallable(() -> entityDao.insert(elementEntity));
		return Single.fromCallable(() -> entityDao.insertOrReplace(elementEntity));

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
