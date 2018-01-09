package com.example.davit_zakaryan.mvvmapp.ui.elements;

import android.content.Context;
import android.content.Intent;
import android.content.res.Configuration;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.example.davit_zakaryan.mvvmapp.FakeData;
import com.example.davit_zakaryan.mvvmapp.R;
import com.example.davit_zakaryan.mvvmapp.data.model.ItemModel;
import com.example.davit_zakaryan.mvvmapp.data.model.ListResponse;
import com.example.davit_zakaryan.mvvmapp.data.service.Repository;
import com.example.davit_zakaryan.mvvmapp.ui.base.BaseViewModel;
import com.example.davit_zakaryan.mvvmapp.ui.base.RecyclerViewViewModel;
import com.example.davit_zakaryan.mvvmapp.ui.element_form.ElementFormActivity;
import com.example.davit_zakaryan.mvvmapp.util.RxBus;

import javax.inject.Inject;

import io.reactivex.Observable;
import io.reactivex.disposables.Disposable;


public class ElementsViewModel implements BaseViewModel, RecyclerViewViewModel {

	private Repository elementsRepository;
	private Context context; // To avoid leaks, this must be an Application Context.
	private ElementsAdapter elementsAdapter;
	private int chosenType; //TODO make intDef
	private Disposable disposable;

	private RxBus rxBus;

	@Inject
	public ElementsViewModel(Repository elementsRepository, Context context) {
		this.elementsRepository = elementsRepository;
		this.context = context.getApplicationContext(); // Force use of Application Context.
	}

	public void setRxBus(RxBus rxBus) {
		this.rxBus = rxBus;
	}

	@Override
	public void onStart() {

		Observable<String> observable = Observable.create(e -> {
			e.onNext("Hello, world!");
			e.onComplete();
		});

		observable.map(s -> s + "ara ")
				.subscribe(System.out::println);

		disposable = elementsRepository
				.getElements()
				.subscribe(this::updateAdapter);
	}

	@Override
	public void onStop() {
		if (!disposable.isDisposed()) {
			disposable.dispose();
		}
	}

	@Override
	public RecyclerView.LayoutManager getLayoutManager() {
		final boolean hasTwoPanes = context.getResources().getBoolean(R.bool.hasTwoPanes);
		int columnCount = 1;
		if (context.getResources().getConfiguration().orientation == Configuration.ORIENTATION_LANDSCAPE && !hasTwoPanes) {
			columnCount = 2;
		}

		return new GridLayoutManager(context, columnCount);
	}

	@Override
	public RecyclerView.Adapter getAdapter() {
		elementsAdapter = new ElementsAdapter(FakeData.getElementList());
		//elementsAdapter.setChangeListener((OnElementSelectionChangeListener) context);
		return elementsAdapter;
	}

	// TODO should be removed to some Handlers class
	public void onClickButtonFab(View view) {
		Intent intent = new Intent(context, ElementFormActivity.class);
		context.startActivity(intent);
	}

	public void updateAdapter(ListResponse<ItemModel> itemModelListResponse) {
		elementsAdapter.setElements(itemModelListResponse.data);
	}

	public void setChosenType(int chosenType) {
		// TODO update list notify adapter
	}

	public int getChosenType() {
		return chosenType;
	}

	///////////////////  UNIT test Part
	public int getSimpleInteger() {
		return 15;
	}

	public void testing(int i) {
	}

	public void someMethod(String s) {
	}
	////////////////////////////////////
}
