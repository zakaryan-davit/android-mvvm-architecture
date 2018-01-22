package com.example.davit_zakaryan.mvvmapp.di.module;

import android.content.Context;
import android.content.SharedPreferences;
import android.databinding.BaseObservable;

import com.example.davit_zakaryan.mvvmapp.data.db.DbOpenHelper;
import com.example.davit_zakaryan.mvvmapp.data.db.model.DaoMaster;
import com.example.davit_zakaryan.mvvmapp.data.db.model.DaoSession;
import com.example.davit_zakaryan.mvvmapp.di.ApplicationContext;
import com.example.davit_zakaryan.mvvmapp.di.DatabaseChangeObservable;
import com.example.davit_zakaryan.mvvmapp.di.DatabaseInfo;
import com.example.davit_zakaryan.mvvmapp.di.PreferenceInfo;
import com.example.davit_zakaryan.mvvmapp.util.Constants;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

@Module
public class DataSourceModule {

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

	@Provides
	@PreferenceInfo
	String providePreferenceName() {
		return Constants.PREF_NAME;
	}

	@Provides
	@Singleton
	SharedPreferences providePreferences(@ApplicationContext Context context, @PreferenceInfo String prefFileName) {
		return context.getSharedPreferences(prefFileName, Context.MODE_PRIVATE);
	}

	@Provides
	@Singleton
	@DatabaseChangeObservable
	BaseObservable provideBaseObservable() {
		return new BaseObservable();
	}

}
