package com.example.davit_zakaryan.mvvmapp.ui.elements;

import android.content.Context;
import android.content.Intent;
import android.content.res.Configuration;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.example.davit_zakaryan.mvvmapp.R;
import com.example.davit_zakaryan.mvvmapp.data.db.DbHelperImpl;
import com.example.davit_zakaryan.mvvmapp.data.db.model.ElementEntity;
import com.example.davit_zakaryan.mvvmapp.data.model.Element;
import com.example.davit_zakaryan.mvvmapp.data.network.NetworkHelper;
import com.example.davit_zakaryan.mvvmapp.data.prefs.PreferencesHelperImpl;
import com.example.davit_zakaryan.mvvmapp.di.ApplicationContext;
import com.example.davit_zakaryan.mvvmapp.ui.base.BaseViewModel;
import com.example.davit_zakaryan.mvvmapp.ui.base.RecyclerViewViewModel;
import com.example.davit_zakaryan.mvvmapp.ui.element_form.ElementFormActivity;
import com.example.davit_zakaryan.mvvmapp.util.ModelDaoConverter;

import java.util.List;

import javax.inject.Inject;

import io.reactivex.Observable;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.disposables.Disposable;

import static com.example.davit_zakaryan.mvvmapp.util.Constants.EXTRA_IS_ELEMENT_CREATED;


public class ElementsViewModel implements BaseViewModel, RecyclerViewViewModel {

	private Context context; // To avoid leaks, this must be an Application Context.
	private NetworkHelper networkHelper;
	private DbHelperImpl dbHelper;
	private PreferencesHelperImpl preferencesHelper;

	private ElementsAdapter elementsAdapter;
	private int chosenType; //TODO make intDef
	private CompositeDisposable disposables = new CompositeDisposable();


	@Inject
	public ElementsViewModel(NetworkHelper networkHelper, @ApplicationContext Context context,
	                         DbHelperImpl dbHelper, PreferencesHelperImpl preferencesHelper,
	                         ElementsAdapter elementsAdapter) {
		this.networkHelper = networkHelper;
		this.context = context.getApplicationContext(); // Force use of Application Context.
		this.dbHelper = dbHelper;
		this.preferencesHelper = preferencesHelper;
		this.elementsAdapter = elementsAdapter;
		//elementsAdapter.setChangeListener((OnElementSelectionChangeListener) context);
	}

	@Override
	public void onStart() {
		Disposable getElementsDisposable;
		if (!preferencesHelper.isDatabaseLoaded()) {
			getElementsDisposable = networkHelper
					.getElements()
					.map(itemModelListResponse -> itemModelListResponse.data)
					.flattenAsObservable(itemModels -> itemModels)
					.map(ModelDaoConverter::convertToElement)
					.toList()
					.subscribe(elements -> {
						Observable.fromIterable(elements)
								.map(ModelDaoConverter::convertToElementEntity)
								.toList()
								.subscribe(this::insertAll);
						updateAdapter(elements);
					});
		} else {
			getElementsDisposable = dbHelper
					.findAll()
					.flattenAsObservable(elementEntities -> elementEntities)
					.map(ModelDaoConverter::convertToElement)
					.toList()
					.subscribe(this::updateAdapter);
		}
		disposables.add(getElementsDisposable);
	}

	@Override
	public void onStop() {
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
		return elementsAdapter;
	}

	// TODO should be removed to some Handlers class
	public void onClickButtonFab(View view) {
		Intent intent = new Intent(context, ElementFormActivity.class);
		intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
		intent.putExtra(EXTRA_IS_ELEMENT_CREATED, false);
		context.startActivity(intent);
	}

	public void updateAdapter(List<Element> domainElements) {
		elementsAdapter.setElements(domainElements);
	}

	public void insertAll(List<ElementEntity> elementEntities) {
		if (!preferencesHelper.isDatabaseLoaded()) {
			dbHelper.insertAll(elementEntities)
					.doOnSuccess(aBoolean -> preferencesHelper.setDatabaseLoaded(aBoolean))
					.subscribe(aBoolean -> System.out.println("Database inserted " + aBoolean));
		}
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
