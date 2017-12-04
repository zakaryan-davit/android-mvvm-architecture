package com.example.davit_zakaryan.mvvmapp;

import android.app.Application;

import com.example.davit_zakaryan.mvvmapp.data.model.Element;
import com.facebook.drawee.backends.pipeline.Fresco;
import com.facebook.stetho.Stetho;

public class App extends Application {

	private static Element INSTANCE;

	public void onCreate() {
		super.onCreate();

		Fresco.initialize(this);

		// A simple initialization.
		if (BuildConfig.DEBUG) {
			Stetho.initializeWithDefaults(this);
		}
	}

	public static Element getElementInstance() {
		if (INSTANCE == null) {
			INSTANCE = new Element();
			INSTANCE.url = "http://www.planwallpaper.com/static/images/desktop-year-of-the-tiger-images-wallpaper.jpg";
			INSTANCE.url = "https://i.neoseeker.com/ca/spellforce_2_dragon_storm_conceptart_RLzl6.jpg";
			INSTANCE.level.set(1);
			INSTANCE.description = "";
		}
		return INSTANCE;
	}
}
