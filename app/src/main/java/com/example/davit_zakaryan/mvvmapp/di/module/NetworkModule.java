package com.example.davit_zakaryan.mvvmapp.di.module;

import com.example.davit_zakaryan.mvvmapp.BuildConfig;
import com.example.davit_zakaryan.mvvmapp.data.service.AppService;
import com.facebook.stetho.okhttp3.StethoInterceptor;

import java.util.concurrent.TimeUnit;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

@Module
public class NetworkModule {

	private String serverURL;// = Constants.BASE_URL;


	public NetworkModule(String serverURL) {
		this.serverURL = serverURL;
	}

	@Provides
	@Singleton
	Retrofit provideRetrofit(OkHttpClient okHttpClient) {
		return new Retrofit.Builder().
				baseUrl(serverURL)
				.client(okHttpClient)
				.addCallAdapterFactory(RxJava2CallAdapterFactory.create())
				.addConverterFactory(GsonConverterFactory.create())
				.build();
	}

	@Provides
	@Singleton
	AppService provideAppService(Retrofit retrofit) {
		return retrofit.create(AppService.class);
	}

	@Provides
	@Singleton
	OkHttpClient provideHttpClient() {
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
