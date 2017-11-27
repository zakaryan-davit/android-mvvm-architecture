package com.example.davit_zakaryan.mvvmapp;

import android.app.Application;

import com.facebook.drawee.backends.pipeline.Fresco;
import com.facebook.stetho.Stetho;

public class App extends Application {
	public void onCreate() {
		super.onCreate();

		Fresco.initialize(this);

		// A simple initialization.
		if (BuildConfig.DEBUG) {
			Stetho.initializeWithDefaults(this);
		}
	}
}
