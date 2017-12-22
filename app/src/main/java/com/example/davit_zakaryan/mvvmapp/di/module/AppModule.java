package com.example.davit_zakaryan.mvvmapp.di.module;

import android.app.Application;

import com.example.davit_zakaryan.mvvmapp.App;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

@Module
public class AppModule {

	private App application;

	public AppModule(App application) {
		this.application = application;
	}

	@Provides
	@Singleton
	Application provideApplication() {
		return application;
	}
}
