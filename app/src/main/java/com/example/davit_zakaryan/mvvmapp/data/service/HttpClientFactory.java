package com.example.davit_zakaryan.mvvmapp.data.service;

import com.example.davit_zakaryan.mvvmapp.BuildConfig;
import com.facebook.stetho.okhttp3.StethoInterceptor;

import java.util.concurrent.TimeUnit;

import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;

public class HttpClientFactory {

	public static OkHttpClient createHttpClient() {

		OkHttpClient.Builder httpClientBuilder = new OkHttpClient.Builder();

		// Or create BuildConfig.LOG_HTTP_REQUESTS
		if (BuildConfig.DEBUG) {
			HttpLoggingInterceptor loggingInterceptor = new HttpLoggingInterceptor();
			loggingInterceptor.setLevel(HttpLoggingInterceptor.Level.BODY);
			httpClientBuilder.networkInterceptors().add(new StethoInterceptor());
			httpClientBuilder.addInterceptor(loggingInterceptor);
		}

		return httpClientBuilder
				.connectTimeout(20, TimeUnit.SECONDS)
				.readTimeout(20, TimeUnit.SECONDS)
				.build();
	}
}
