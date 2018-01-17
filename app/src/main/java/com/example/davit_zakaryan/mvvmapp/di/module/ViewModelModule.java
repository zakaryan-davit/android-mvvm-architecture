package com.example.davit_zakaryan.mvvmapp.di.module;

import com.example.davit_zakaryan.mvvmapp.data.network.NetworkHelper;
import com.example.davit_zakaryan.mvvmapp.data.network.NetworkHelperImpl;
import com.example.davit_zakaryan.mvvmapp.data.service.AppService;
import com.example.davit_zakaryan.mvvmapp.ui.base.HomeViewModel;

import dagger.Module;
import dagger.Provides;

@Module
public class ViewModelModule {


	@Provides
	HomeViewModel provideHomeViewModel() {
		return new HomeViewModel();
	}

	// Could be commented if there is constructor with Inject
//	@Provides
//	ElementsViewModel provideElementsViewModel(NetworkHelper repository, Context context) {
//		return new ElementsViewModel(repository, context);
//	}

	@Provides
	NetworkHelper provideRepository(AppService appService) {
		return new NetworkHelperImpl(appService);
	}
}
