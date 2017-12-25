package com.example.davit_zakaryan.mvvmapp.di.component;

import android.app.Application;

import com.example.davit_zakaryan.mvvmapp.data.service.AppService;
import com.example.davit_zakaryan.mvvmapp.di.module.AppModule;
import com.example.davit_zakaryan.mvvmapp.di.module.NetworkModule;

import javax.inject.Singleton;

import dagger.Component;

@Singleton
@Component(modules = {AppModule.class, NetworkModule.class})
public interface AppComponent {

	//void inject(App application);

	//Dependencies below should be visible out of the component
	Application getApplication();

	AppService getAppService();
}
