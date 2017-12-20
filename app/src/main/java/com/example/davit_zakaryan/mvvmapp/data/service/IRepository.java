package com.example.davit_zakaryan.mvvmapp.data.service;

import android.support.annotation.NonNull;

import com.example.davit_zakaryan.mvvmapp.data.model.ItemModel;

import java.util.List;

public interface IRepository {

	interface LoadElementsCallback {

		void onElementsLoaded(List<ItemModel> posts);
	}

	interface AddElementCallback {

		void onElementAdded(ItemModel element);
	}

	void getElements(@NonNull LoadElementsCallback callback, boolean loadMore, boolean forceUpdate);

	void getElements(@NonNull LoadElementsCallback callback);

	void addElement(ItemModel itemModel, @NonNull AddElementCallback callback);
}
