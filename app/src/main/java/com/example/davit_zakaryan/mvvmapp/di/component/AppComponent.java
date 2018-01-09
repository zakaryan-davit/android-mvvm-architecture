package com.example.davit_zakaryan.mvvmapp.di.component;

import android.app.Application;

import com.example.davit_zakaryan.mvvmapp.data.service.AppService;
import com.example.davit_zakaryan.mvvmapp.di.module.AppModule;
import com.example.davit_zakaryan.mvvmapp.di.module.NetworkModule;
import com.example.davit_zakaryan.mvvmapp.di.module.ViewModelModule;
import com.example.davit_zakaryan.mvvmapp.ui.element_details.ElementDetailsFragment;
import com.example.davit_zakaryan.mvvmapp.ui.element_form.ElementFormFragment;
import com.example.davit_zakaryan.mvvmapp.ui.elements.ElementsFragment;

import javax.inject.Singleton;

import dagger.Component;

@Singleton
@Component(modules = {AppModule.class, NetworkModule.class, ViewModelModule.class})
public interface AppComponent {

	void inject(ElementsFragment fragment);

	void inject(ElementDetailsFragment fragment);

	void inject(ElementFormFragment fragment);


	//Dependencies below should be visible out of the component
	Application getApplication();

	AppService getAppService();
}
