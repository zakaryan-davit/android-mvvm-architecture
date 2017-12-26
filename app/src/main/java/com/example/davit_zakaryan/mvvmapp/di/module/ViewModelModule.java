package com.example.davit_zakaryan.mvvmapp.di.module;

import android.content.Context;

import com.example.davit_zakaryan.mvvmapp.data.service.AppService;
import com.example.davit_zakaryan.mvvmapp.data.service.Repository;
import com.example.davit_zakaryan.mvvmapp.ui.base.HomeViewModel;
import com.example.davit_zakaryan.mvvmapp.ui.elements.ElementsViewModel;
import com.example.davit_zakaryan.mvvmapp.ui.elements.RepositoryImpl;

import dagger.Module;
import dagger.Provides;

@Module
public class ViewModelModule {


	@Provides
	HomeViewModel provideHomeViewModel() {
		return new HomeViewModel();
	}

	@Provides
	ElementsViewModel provideElementsViewModel(Repository repository, Context context) {
		return new ElementsViewModel(repository, context);
	}

	@Provides
	Repository provideRepository(AppService appService) {
		return new RepositoryImpl(appService);
	}
}
