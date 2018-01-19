package com.example.davit_zakaryan.mvvmapp.ui.elements;

import android.content.Context;
import android.content.Intent;
import android.content.res.Configuration;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.example.davit_zakaryan.mvvmapp.R;
import com.example.davit_zakaryan.mvvmapp.data.DataSource;
import com.example.davit_zakaryan.mvvmapp.data.model.Element;
import com.example.davit_zakaryan.mvvmapp.di.ApplicationContext;
import com.example.davit_zakaryan.mvvmapp.ui.base.BaseViewModel;
import com.example.davit_zakaryan.mvvmapp.ui.base.RecyclerViewViewModel;
import com.example.davit_zakaryan.mvvmapp.ui.element_form.ElementFormActivity;

import java.util.List;

import javax.inject.Inject;

import io.reactivex.disposables.CompositeDisposable;

import static com.example.davit_zakaryan.mvvmapp.util.Constants.EXTRA_IS_ELEMENT_CREATED;


public class ElementsViewModel implements BaseViewModel, RecyclerViewViewModel {

	private Context context; // To avoid leaks, this must be an Application Context.
	private DataSource dataSource;

	private ElementsAdapter elementsAdapter;
	private int chosenType; //TODO make intDef
	private CompositeDisposable disposables = new CompositeDisposable();


	@Inject
	public ElementsViewModel(@ApplicationContext Context context, ElementsAdapter elementsAdapter,
	                         DataSource dataSource) {
		this.context = context.getApplicationContext(); // Force use of Application Context.
		this.dataSource = dataSource;
		this.elementsAdapter = elementsAdapter;
		//elementsAdapter.setChangeListener((OnElementSelectionChangeListener) context);
	}

	@Override
	public void onStart() {
		dataSource.getElementListSingle().subscribe(this::updateAdapter);
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
