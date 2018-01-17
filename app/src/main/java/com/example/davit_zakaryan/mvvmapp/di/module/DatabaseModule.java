package com.example.davit_zakaryan.mvvmapp.di.module;

import com.example.davit_zakaryan.mvvmapp.data.db.DbOpenHelper;
import com.example.davit_zakaryan.mvvmapp.data.db.model.DaoMaster;
import com.example.davit_zakaryan.mvvmapp.data.db.model.DaoSession;
import com.example.davit_zakaryan.mvvmapp.di.DatabaseInfo;
import com.example.davit_zakaryan.mvvmapp.util.Constants;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

@Module
public class DatabaseModule {

	@Provides
	@Singleton
	DaoSession provideDaoSession(DaoMaster daoMaster) {
		return daoMaster.newSession();
	}

	@Provides
	@Singleton
	DaoMaster provideDaoMaster(DbOpenHelper dbOpenHelper) {
		return new DaoMaster(dbOpenHelper.getWritableDb());
	}

	@Provides
	@DatabaseInfo
	String provideDatabaseName() {
		return Constants.DB_NAME;
	}

}
