package com.example.davit_zakaryan.mvvmapp.di.module;

import android.app.Application;
import android.content.Context;

import com.example.davit_zakaryan.mvvmapp.di.ApplicationContext;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

@Module
public class AppModule {

	private Application application;

	public AppModule(Application application) {
		this.application = application;
	}

	@Provides
	@Singleton
	Application provideApplication() {
		return application;
	}

	@Provides
	@ApplicationContext
	Context provideContext() {
		return application.getApplicationContext();
	}
}
