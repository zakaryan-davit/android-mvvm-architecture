package com.example.davit_zakaryan.mvvmapp;

import android.app.Application;

import com.facebook.stetho.Stetho;

public class App extends Application {
	public void onCreate() {
		super.onCreate();

		// A simple initialization.
		if (BuildConfig.DEBUG) {
			Stetho.initializeWithDefaults(this);
		}
	}
}
