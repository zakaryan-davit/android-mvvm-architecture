package com.example.davit_zakaryan.mvvmapp.data.service;

import android.support.annotation.NonNull;

import com.example.davit_zakaryan.mvvmapp.data.model.ItemModel;
import com.example.davit_zakaryan.mvvmapp.data.model.ListResponse;

import java.util.List;

import io.reactivex.Single;

public interface IRepository {

	interface LoadElementsCallback {

		void onElementsLoaded(List<ItemModel> posts);
	}

	interface AddElementCallback {

		void onElementAdded(ItemModel element);
	}

	void getElements(@NonNull LoadElementsCallback callback, boolean loadMore, boolean forceUpdate);

	Single<ListResponse<ItemModel>> getElements();

	void addElement(ItemModel itemModel, @NonNull AddElementCallback callback);
}
