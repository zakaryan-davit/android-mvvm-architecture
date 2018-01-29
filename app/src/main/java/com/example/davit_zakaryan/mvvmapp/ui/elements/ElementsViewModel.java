package com.example.davit_zakaryan.mvvmapp.ui.elements;

import android.content.Context;
import android.content.Intent;
import android.content.res.Configuration;
import android.databinding.Observable;
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
import io.reactivex.subjects.PublishSubject;

import static com.example.davit_zakaryan.mvvmapp.util.Constants.EXTRA_IS_ELEMENT_CREATED;


public class ElementsViewModel extends BaseViewModel implements RecyclerViewViewModel {

	private Context context; // To avoid leaks, this must be an Application Context.
	private ElementsAdapter elementsAdapter;
	private int chosenType; //TODO make intDef
	private final Observable.OnPropertyChangedCallback callback;
	//private PublishSubject<Integer> subject;


	@Inject
	public ElementsViewModel(@ApplicationContext Context context, ElementsAdapter elementsAdapter,
	                         DataSource dataSource, CompositeDisposable disposables) {
		super(dataSource, disposables);
		this.context = context.getApplicationContext(); // Force use of Application Context.
		this.elementsAdapter = elementsAdapter;
		callback = new Observable.OnPropertyChangedCallback() {
			@Override
			public void onPropertyChanged(Observable observable, int i) {
				disposables.add(dataSource.getElementListSingle().subscribe(elements -> updateAdapter(elements)));
			}
		};
		dataSource.databaseChangeObservable.addOnPropertyChangedCallback(callback);
	}

	PublishSubject<Element> getElementStream() {
		return elementsAdapter.getItemClickSubject();
	}

	@Override
	public void onStart() {
		disposables.add(dataSource.getElementListSingle().subscribe(this::updateAdapter));
	}

	@Override
	protected void onStop() {
		super.onStop();
		dataSource.databaseChangeObservable.removeOnPropertyChangedCallback(callback);
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
		// TODO add fragment transaction
		//  Bundle bundle = new Bundle();
		//  bundle.putInt(Constants.EXTRA_CHOSEN_TYPE, chosenType);
		//	ElementDetailsFragment fragment = new ElementDetailsFragment();
		//	fragment.setArguments(bundle);
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
