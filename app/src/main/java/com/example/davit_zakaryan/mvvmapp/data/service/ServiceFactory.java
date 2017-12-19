package com.example.davit_zakaryan.mvvmapp.data.service;

import com.example.davit_zakaryan.mvvmapp.util.Constants;

import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by Davit_Zakaryan on 12/19/2017.
 */
public class ServiceFactory {

	private static final Retrofit RETROFIT = createRetrofitInstance(Constants.BASE_URL);
	private static AppService appService;

	public static AppService getAppService() {
		if (appService == null) {
			appService = RETROFIT.create(AppService.class);
		}
		return appService;
	}

	private static Retrofit createRetrofitInstance(String serverURL) {
		return new Retrofit.Builder().
				baseUrl(serverURL)
				.client(HttpClientFactory.createHttpClient())
				.addCallAdapterFactory(RxJava2CallAdapterFactory.create())
				.addConverterFactory(GsonConverterFactory.create())
				.build();
	}

}
