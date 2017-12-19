package com.example.davit_zakaryan.mvvmapp.ui.elements;

import com.example.davit_zakaryan.mvvmapp.data.model.ItemsResponse;
import com.example.davit_zakaryan.mvvmapp.data.model.ListResponse;
import com.example.davit_zakaryan.mvvmapp.data.service.IRepository;
import com.example.davit_zakaryan.mvvmapp.data.service.ServiceFactory;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.observers.DefaultObserver;
import io.reactivex.schedulers.Schedulers;

/**
 * Created by Davit_Zakaryan on 12/19/2017.
 */
public class ElementsRepository implements IRepository {

	@Override
	public void getElements() {
		ServiceFactory.getAppService()
				.getItems()
				.subscribeOn(Schedulers.newThread())
				.observeOn(AndroidSchedulers.mainThread())
				.subscribe(new DefaultObserver<ListResponse<ItemsResponse>>() {
					@Override
					public void onNext(ListResponse<ItemsResponse> itemsResponseListResponse) {

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
