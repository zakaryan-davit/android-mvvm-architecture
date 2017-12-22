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
import com.example.davit_zakaryan.mvvmapp.data.service.IRepository;
import com.example.davit_zakaryan.mvvmapp.ui.base.BaseViewModel;
import com.example.davit_zakaryan.mvvmapp.ui.base.RecyclerViewViewModel;
import com.example.davit_zakaryan.mvvmapp.ui.element_form.ElementFormActivity;

import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Consumer;


public class ElementsViewModel implements BaseViewModel, RecyclerViewViewModel {

	private IRepository elementsRepository;
	private Context context; // To avoid leaks, this must be an Application Context.
	private ElementsAdapter elementsAdapter;
	private int chosenType; //TODO make intDef
	private Disposable disposable;

	ElementsViewModel(IRepository elementsRepository, Context context) {
		this.elementsRepository = elementsRepository;
		this.context = context.getApplicationContext(); // Force use of Application Context.
	}

	@Override
	public void onStart() {
		disposable = elementsRepository.getElements().subscribe(new Consumer<ListResponse<ItemModel>>() {
			@Override
			public void accept(ListResponse<ItemModel> itemModelListResponse) throws Exception {
				elementsAdapter.setElements(itemModelListResponse.data);

			}
		}, new Consumer<Throwable>() {
			@Override
			public void accept(Throwable throwable) throws Exception {

			}
		});
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

	public void setChosenType(int chosenType) {
		elementsAdapter.setChosenType(chosenType);
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
