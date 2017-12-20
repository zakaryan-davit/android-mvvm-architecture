package com.example.davit_zakaryan.mvvmapp.data.service;

import com.example.davit_zakaryan.mvvmapp.data.model.ItemModel;
import com.example.davit_zakaryan.mvvmapp.data.model.ListResponse;
import com.example.davit_zakaryan.mvvmapp.data.model.ObjectResponse;

import io.reactivex.Observable;
import retrofit2.http.Body;
import retrofit2.http.POST;

public interface AppService {

	@POST("items")
	Observable<ListResponse<ItemModel>> getItems();

	@POST("addItem")
	Observable<ObjectResponse<ItemModel>> addItem(@Body ItemModel itemModel);

	@POST("editItem")
	Observable<ListResponse<ItemModel>> editItem();

}
