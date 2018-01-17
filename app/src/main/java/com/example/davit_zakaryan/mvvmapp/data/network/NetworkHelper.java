package com.example.davit_zakaryan.mvvmapp.data.network;

import com.example.davit_zakaryan.mvvmapp.data.network.model.ItemModel;
import com.example.davit_zakaryan.mvvmapp.data.network.model.ListResponse;
import com.example.davit_zakaryan.mvvmapp.data.network.model.ObjectResponse;

import io.reactivex.Single;

public interface NetworkHelper {

	//TODO boolean loadMore, boolean forceUpdate
	Single<ListResponse<ItemModel>> getElements();

	Single<ObjectResponse<ItemModel>> addElement(ItemModel itemModel);
}
