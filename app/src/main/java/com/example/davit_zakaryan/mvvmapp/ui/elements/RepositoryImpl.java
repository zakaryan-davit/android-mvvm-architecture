package com.example.davit_zakaryan.mvvmapp.ui.elements;

import com.example.davit_zakaryan.mvvmapp.data.model.ItemModel;
import com.example.davit_zakaryan.mvvmapp.data.model.ListResponse;
import com.example.davit_zakaryan.mvvmapp.data.model.ObjectResponse;
import com.example.davit_zakaryan.mvvmapp.data.service.AppService;
import com.example.davit_zakaryan.mvvmapp.data.service.Repository;

import io.reactivex.Single;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;

/**
 * Created by Davit_Zakaryan on 12/19/2017.
 */
public class RepositoryImpl implements Repository {

	private AppService appService;

	public RepositoryImpl(AppService appService) {
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
