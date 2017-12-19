package com.example.davit_zakaryan.mvvmapp.data.service;

import android.support.annotation.NonNull;

import com.example.davit_zakaryan.mvvmapp.data.model.ItemsResponse;

import java.util.List;

public interface IRepository {

	interface LoadElementsCallback {

		void onElementsLoaded(List<ItemsResponse> posts);
	}

	void getElements(@NonNull LoadElementsCallback callback, boolean loadMore, boolean forceUpdate);

	void getElements(@NonNull LoadElementsCallback callback);
}
