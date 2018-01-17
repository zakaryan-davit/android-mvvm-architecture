package com.example.davit_zakaryan.mvvmapp.data.network;

import com.example.davit_zakaryan.mvvmapp.data.network.model.ItemModel;
import com.example.davit_zakaryan.mvvmapp.data.network.model.ListResponse;
import com.example.davit_zakaryan.mvvmapp.data.network.model.ObjectResponse;
import com.example.davit_zakaryan.mvvmapp.data.service.AppService;

import javax.inject.Singleton;

import io.reactivex.Single;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;

@Singleton
public class NetworkHelperImpl implements NetworkHelper {

	private AppService appService;

	public NetworkHelperImpl(AppService appService) {
		this.appService = appService;
	}

	@Override
	public Single<ListResponse<ItemModel>> getElements() {
		return appService
				.getItems()
				.subscribeOn(Schedulers.newThread())
				.observeOn(AndroidSchedulers.mainThread());
	}

	@Override
	public Single<ObjectResponse<ItemModel>> addElement(ItemModel itemModel) {
		return appService
				.addItem(itemModel)
				.subscribeOn(Schedulers.newThread())
				.observeOn(AndroidSchedulers.mainThread());
	}


}
