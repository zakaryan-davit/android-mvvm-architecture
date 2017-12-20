package com.example.davit_zakaryan.mvvmapp.data.service;

import com.example.davit_zakaryan.mvvmapp.data.model.ItemsResponse;
import com.example.davit_zakaryan.mvvmapp.data.model.ListResponse;

import io.reactivex.Observable;
import retrofit2.http.POST;

/**
 * Created by Davit_Zakaryan on 12/19/2017.
 */
public interface AppService {

	@POST("items")
	Observable<ListResponse<ItemsResponse>> getItems();
}
