package com.example.davit_zakaryan.mvvmapp.ui.element_form;

import android.content.Context;
import android.databinding.ObservableField;

import com.example.davit_zakaryan.mvvmapp.data.model.Element;
import com.example.davit_zakaryan.mvvmapp.data.model.ItemModel;
import com.example.davit_zakaryan.mvvmapp.data.model.ObjectResponse;
import com.example.davit_zakaryan.mvvmapp.data.service.Repository;
import com.example.davit_zakaryan.mvvmapp.ui.base.BaseViewModel;
import com.example.davit_zakaryan.mvvmapp.util.ModelDaoConverter;

import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Consumer;

public class ElementFormViewModel implements BaseViewModel {

	private ObservableField<Element> elementObservable = new ObservableField<>();
	private Repository repository;
	private Context context;
	private Disposable disposable;

	ElementFormViewModel(Repository repository, Context context, boolean isCreated) {
		this.context = context.getApplicationContext(); // Force use of Application Context.
		this.repository = repository;
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
		//this.element = repository.addElement();
	}

	@Override
	public void onStop() {
		if (!disposable.isDisposed()) {
			disposable.dispose();
		}
	}


	public void onClickButtonSave(Element element) {
		disposable = repository.addElement(ModelDaoConverter.convertElement(element))
				.subscribe(new Consumer<ObjectResponse<ItemModel>>() {
					@Override
					public void accept(ObjectResponse<ItemModel> itemModelObjectResponse) throws Exception {
						System.out.println("success");
					}
				});
	}

}
