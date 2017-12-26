package com.example.davit_zakaryan.mvvmapp.data.service;

import com.example.davit_zakaryan.mvvmapp.data.model.ItemModel;
import com.example.davit_zakaryan.mvvmapp.data.model.ListResponse;
import com.example.davit_zakaryan.mvvmapp.data.model.ObjectResponse;

import io.reactivex.Single;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;

public interface AppService {

	@GET("items")
	Single<ListResponse<ItemModel>> getItems();

	@POST("addItem")
	Single<ObjectResponse<ItemModel>> addItem(@Body ItemModel itemModel);

	@POST("editItem")
	Single<ListResponse<ItemModel>> editItem();

}
