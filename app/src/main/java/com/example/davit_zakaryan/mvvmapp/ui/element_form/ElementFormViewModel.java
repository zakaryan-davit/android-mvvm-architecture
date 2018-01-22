package com.example.davit_zakaryan.mvvmapp.ui.element_form;

import android.content.Context;
import android.databinding.ObservableField;
import android.support.annotation.NonNull;

import com.example.davit_zakaryan.mvvmapp.data.DataSource;
import com.example.davit_zakaryan.mvvmapp.data.model.Element;
import com.example.davit_zakaryan.mvvmapp.di.ApplicationContext;
import com.example.davit_zakaryan.mvvmapp.ui.base.BaseViewModel;

import javax.inject.Inject;

import io.reactivex.disposables.CompositeDisposable;

public class ElementFormViewModel extends BaseViewModel {

	private Context context;
	public boolean isCreated;
	private ObservableField<Element> elementObservable = new ObservableField<>();

	@Inject
	ElementFormViewModel(@ApplicationContext Context context,
	                     DataSource dataSource, CompositeDisposable disposables) {
		super(dataSource, disposables);
		this.context = context.getApplicationContext(); // Force use of Application Context.
		if (!isCreated) {
			this.elementObservable = new ObservableField<>(new Element());
			elementObservable.get().url.set("http://www.planwallpaper.com/static/images/desktop-year-of-the-tiger-images-wallpaper.jpg");
		}
	}

	public Element getElement() {
		return elementObservable.get();
	}

	@Override
	public void onStart() {
		//this.element = networkHelper.addElement();
	}

	public void onClickButtonSave(@NonNull Element element) {
		disposables.add(dataSource.insertElement(element).subscribe(insertId -> System.out.println("insertId long === " + insertId)));
	}

}
