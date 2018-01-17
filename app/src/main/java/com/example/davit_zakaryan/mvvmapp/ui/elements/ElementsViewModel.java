package com.example.davit_zakaryan.mvvmapp.ui.elements;

import android.content.Context;
import android.content.Intent;
import android.content.res.Configuration;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.example.davit_zakaryan.mvvmapp.R;
import com.example.davit_zakaryan.mvvmapp.data.db.DbHelperImpl;
import com.example.davit_zakaryan.mvvmapp.data.db.model.Element;
import com.example.davit_zakaryan.mvvmapp.data.network.model.ItemModel;
import com.example.davit_zakaryan.mvvmapp.data.network.model.ListResponse;
import com.example.davit_zakaryan.mvvmapp.data.service.Repository;
import com.example.davit_zakaryan.mvvmapp.di.ApplicationContext;
import com.example.davit_zakaryan.mvvmapp.ui.base.BaseViewModel;
import com.example.davit_zakaryan.mvvmapp.ui.base.RecyclerViewViewModel;
import com.example.davit_zakaryan.mvvmapp.ui.element_form.ElementFormActivity;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.disposables.Disposable;


public class ElementsViewModel implements BaseViewModel, RecyclerViewViewModel {

	private Context context; // To avoid leaks, this must be an Application Context.
	private Repository elementsRepository;
	private DbHelperImpl dbHelper;

	private ElementsAdapter elementsAdapter;
	private int chosenType; //TODO make intDef
	private Disposable disposable;
	private CompositeDisposable disposables = new CompositeDisposable();


	@Inject
	public ElementsViewModel(Repository elementsRepository, @ApplicationContext Context context, DbHelperImpl dbHelper) {
		this.elementsRepository = elementsRepository;
		this.context = context.getApplicationContext(); // Force use of Application Context.
		this.dbHelper = dbHelper;
	}

	@Override
	public void onStart() {
		disposable = elementsRepository
				.getElements()
				.subscribe(this::updateAdapter);
		disposables.add(disposable);
	}

	@Override
	public void onStop() {
//		if (!disposable.isDisposed()) {
//			disposable.dispose();
//		}
		disposables.dispose();
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
		elementsAdapter = new ElementsAdapter();
		//elementsAdapter.setChangeListener((OnElementSelectionChangeListener) context);
		return elementsAdapter;
	}

	// TODO should be removed to some Handlers class
	public void onClickButtonFab(View view) {
		Intent intent = new Intent(context, ElementFormActivity.class);
		intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
		context.startActivity(intent);
	}

	public void updateAdapter(ListResponse<ItemModel> itemModelListResponse) {

		List<Element> elements = new ArrayList<>();
		Element element = new Element();

		element.setTitle("sdlkjsldkfj");

		elements.add(element);

		//elements.add(element);


		dbHelper.insertAll(elements)
				.subscribe(aBoolean -> dbHelper.findAll().subscribe(elements1 -> {

				}));

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
