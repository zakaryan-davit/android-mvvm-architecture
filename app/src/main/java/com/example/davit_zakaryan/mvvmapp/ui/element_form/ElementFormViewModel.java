package com.example.davit_zakaryan.mvvmapp.ui.element_form;

import android.content.Context;
import android.databinding.ObservableField;
import android.support.annotation.NonNull;

import com.example.davit_zakaryan.mvvmapp.data.db.DbHelper;
import com.example.davit_zakaryan.mvvmapp.data.db.DbHelperImpl;
import com.example.davit_zakaryan.mvvmapp.data.model.Element;
import com.example.davit_zakaryan.mvvmapp.data.network.NetworkHelper;
import com.example.davit_zakaryan.mvvmapp.di.ApplicationContext;
import com.example.davit_zakaryan.mvvmapp.ui.base.BaseViewModel;
import com.example.davit_zakaryan.mvvmapp.util.ModelDaoConverter;

import javax.inject.Inject;

import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Consumer;

public class ElementFormViewModel implements BaseViewModel {

	public boolean isCreated;
	private ObservableField<Element> elementObservable = new ObservableField<>();
	private NetworkHelper networkHelper;
	private DbHelper dbHelper;
	private Context context;
	private Disposable disposable;

	@Inject
	ElementFormViewModel(NetworkHelper repository, @ApplicationContext Context context,
	                     DbHelperImpl dbHelper) {
		this.context = context.getApplicationContext(); // Force use of Application Context.
		this.networkHelper = repository;
		this.dbHelper = dbHelper;
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

	@Override
	public void onStop() {
		if (!disposable.isDisposed()) {
			disposable.dispose();
		}
	}


	public void onClickButtonSave(@NonNull Element element) {
		//TODO maybe needed
		//disposable = networkHelper
		//		.addElement(ModelDaoConverter.convertToItemModel(element))
		//		.subscribe(itemModelObjectResponse -> System.out.println("success"));

		disposable = dbHelper.insertElement(ModelDaoConverter.convertToElementEntity(element))
				.subscribe(new Consumer<Long>() {
					@Override
					public void accept(Long aLong) throws Exception {
						System.out.println("aflhslfhlkjgklgjker   long === " + aLong);
					}
				});
	}

}
