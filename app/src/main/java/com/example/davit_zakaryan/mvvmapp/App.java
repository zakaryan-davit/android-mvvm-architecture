package com.example.davit_zakaryan.mvvmapp;

import android.app.Application;
import android.content.Context;

import com.example.davit_zakaryan.mvvmapp.di.component.AppComponent;
import com.example.davit_zakaryan.mvvmapp.di.component.DaggerAppComponent;
import com.example.davit_zakaryan.mvvmapp.di.module.AppModule;
import com.example.davit_zakaryan.mvvmapp.di.module.NetworkModule;
import com.example.davit_zakaryan.mvvmapp.util.Constants;
import com.facebook.drawee.backends.pipeline.Fresco;
import com.facebook.stetho.Stetho;

public class App extends Application {

	private AppComponent appComponent;

	public static App get(Context context) {
		return (App) context.getApplicationContext();
	}


	public void onCreate() {
		super.onCreate();

		Fresco.initialize(this);

		// A simple initialization.
		if (BuildConfig.DEBUG) {
			Stetho.initializeWithDefaults(this);
		}

		//Global dependencies graph is created here
		appComponent = DaggerAppComponent.builder()
				.appModule(new AppModule(this))
				.networkModule(new NetworkModule(Constants.BASE_URL))
				//.githubApiModule(new GithubApiModule()) //Can be removed because of no-arg constructor
				.build();

	}

	//Just a helper to provide appComponent to local components which depend on it
	public AppComponent getAppComponent() {
		return appComponent;
	}
}
