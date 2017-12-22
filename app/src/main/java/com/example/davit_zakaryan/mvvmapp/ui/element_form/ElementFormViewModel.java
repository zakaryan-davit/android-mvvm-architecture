package com.example.davit_zakaryan.mvvmapp.ui.element_form;

import android.content.Context;
import android.databinding.ObservableField;

import com.example.davit_zakaryan.mvvmapp.data.model.Element;
import com.example.davit_zakaryan.mvvmapp.data.model.ItemModel;
import com.example.davit_zakaryan.mvvmapp.data.service.IRepository;
import com.example.davit_zakaryan.mvvmapp.ui.base.BaseViewModel;
import com.example.davit_zakaryan.mvvmapp.util.ModelDaoConverter;

public class ElementFormViewModel implements BaseViewModel {

	private ObservableField<Element> elementObservable = new ObservableField<>();
	private IRepository repository;
	private Context context;

	ElementFormViewModel(IRepository repository, Context context, boolean isCreated) {
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

	}


	public void onClickButtonSave(Element element) {
		repository.addElement(ModelDaoConverter.convertElement(element), new IRepository.AddElementCallback() {
			@Override
			public void onElementAdded(ItemModel element) {
				System.out.println("success");
			}
		});
	}

}
