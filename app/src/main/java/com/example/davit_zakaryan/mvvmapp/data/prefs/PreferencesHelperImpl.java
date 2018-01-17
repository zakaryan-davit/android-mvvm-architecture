package com.example.davit_zakaryan.mvvmapp.data.prefs;

import android.content.Context;
import android.content.SharedPreferences;

import com.example.davit_zakaryan.mvvmapp.di.ApplicationContext;
import com.example.davit_zakaryan.mvvmapp.di.PreferenceInfo;

import javax.inject.Inject;


public class PreferencesHelperImpl implements PreferencesHelper {

	private static final String PREFS_IS_DATABASE_LOADED = "prefs.is.data.loaded";
	private final SharedPreferences prefs;

	@Inject
	PreferencesHelperImpl(@ApplicationContext Context context, @PreferenceInfo String prefFileName) {
		prefs = context.getSharedPreferences(prefFileName, Context.MODE_PRIVATE);
	}

	@Override
	public boolean isDatabaseLoaded() {
		return prefs.getBoolean(PREFS_IS_DATABASE_LOADED, false);
	}

	@Override
	public void setDatabaseLoaded(boolean isDatabasedLoaded) {
		prefs.edit().putBoolean(PREFS_IS_DATABASE_LOADED, isDatabasedLoaded).apply();
	}
}
