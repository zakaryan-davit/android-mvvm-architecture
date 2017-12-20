package com.example.davit_zakaryan.mvvmapp.ui.elements;

import android.support.annotation.NonNull;

import com.example.davit_zakaryan.mvvmapp.data.model.ItemModel;
import com.example.davit_zakaryan.mvvmapp.data.model.ListResponse;
import com.example.davit_zakaryan.mvvmapp.data.model.ObjectResponse;
import com.example.davit_zakaryan.mvvmapp.data.service.IRepository;
import com.example.davit_zakaryan.mvvmapp.data.service.ServiceFactory;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.observers.DefaultObserver;
import io.reactivex.schedulers.Schedulers;

/**
 * Created by Davit_Zakaryan on 12/19/2017.
 */
public class RepositoryImpl implements IRepository {

	@Override
	public void getElements(@NonNull LoadElementsCallback callback, boolean loadMore, boolean forceUpdate) {

	}

	@Override
	public void getElements(@NonNull final LoadElementsCallback callback) {
		ServiceFactory.getAppService()
				.getItems()
				.subscribeOn(Schedulers.newThread())
				.observeOn(AndroidSchedulers.mainThread())
				.subscribe(new DefaultObserver<ListResponse<ItemModel>>() {
					@Override
					public void onNext(ListResponse<ItemModel> itemsResponseListResponse) {
						callback.onElementsLoaded(itemsResponseListResponse.data);
					}

					@Override
					public void onError(Throwable e) {

					}

					@Override
					public void onComplete() {

					}
				});
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
