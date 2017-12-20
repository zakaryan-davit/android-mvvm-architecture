package com.example.davit_zakaryan.mvvmapp.ui.element_form;

import android.content.Context;
import android.databinding.Observable;
import android.databinding.ObservableField;
import android.view.View;

import com.example.davit_zakaryan.mvvmapp.data.model.Element;
import com.example.davit_zakaryan.mvvmapp.data.model.ItemModel;
import com.example.davit_zakaryan.mvvmapp.data.service.IRepository;
import com.example.davit_zakaryan.mvvmapp.ui.base.BaseViewModel;
import com.example.davit_zakaryan.mvvmapp.util.ModelDaoConverter;

/**
 * Created by Davit_Zakaryan on 12/13/2017.
 */
public class ElementFormViewModel implements BaseViewModel {

	private ObservableField<Element> elementObservable = new ObservableField<>();
	private IRepository repository;
	private Context context;
	private boolean isCreated;

	ElementFormViewModel(IRepository repository, Context context, boolean isCreated) {
		this.context = context.getApplicationContext(); // Force use of Application Context.
		this.repository = repository;
		this.isCreated = isCreated;
		if (!isCreated) {
			this.elementObservable = new ObservableField<>(new Element());
			elementObservable.get().url.set("http://www.planwallpaper.com/static/images/desktop-year-of-the-tiger-images-wallpaper.jpg");
		}

		elementObservable.addOnPropertyChangedCallback(new Observable.OnPropertyChangedCallback() {
			@Override
			public void onPropertyChanged(Observable observable, int i) {
				Element element = elementObservable.get();
				//element.name =
			}
		});
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

	}


	public void onClickButtonSave(View view) {
		repository.addElement(ModelDaoConverter.convertElement(getElement()), new IRepository.AddElementCallback() {
			@Override
			public void onElementAdded(ItemModel element) {
				System.out.println("success");
			}
		});
	}

}
