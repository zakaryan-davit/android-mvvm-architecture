package com.example.davit_zakaryan.mvvmapp.ui.elements;

import android.support.annotation.NonNull;

import com.example.davit_zakaryan.mvvmapp.data.model.ItemModel;
import com.example.davit_zakaryan.mvvmapp.data.model.ListResponse;
import com.example.davit_zakaryan.mvvmapp.data.model.ObjectResponse;
import com.example.davit_zakaryan.mvvmapp.data.service.AppService;
import com.example.davit_zakaryan.mvvmapp.data.service.IRepository;
import com.example.davit_zakaryan.mvvmapp.data.service.ServiceFactory;

import io.reactivex.Single;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.observers.DefaultObserver;
import io.reactivex.schedulers.Schedulers;

/**
 * Created by Davit_Zakaryan on 12/19/2017.
 */
public class RepositoryImpl implements IRepository {


	AppService appService;

	public RepositoryImpl(AppService appService) {
		this.appService = appService;
	}

	@Override
	public void getElements(@NonNull LoadElementsCallback callback, boolean loadMore, boolean forceUpdate) {

	}

	@Override
	public Single<ListResponse<ItemModel>> getElements() {
		return appService
				.getItems()
				.subscribeOn(Schedulers.newThread())
				.observeOn(AndroidSchedulers.mainThread());
	}

	@Override
	public void addElement(ItemModel itemModel, @NonNull final AddElementCallback callback) {
		ServiceFactory.getAppService()
				.addItem(itemModel)
				.subscribeOn(Schedulers.newThread())
				.observeOn(AndroidSchedulers.mainThread())
				.subscribe(new DefaultObserver<ObjectResponse<ItemModel>>() {
					@Override
					public void onNext(ObjectResponse<ItemModel> itemModelObjectResponse) {
						callback.onElementAdded(itemModelObjectResponse.data);
					}

					@Override
					public void onError(Throwable e) {

					}

					@Override
					public void onComplete() {

					}
				});
	}


}
