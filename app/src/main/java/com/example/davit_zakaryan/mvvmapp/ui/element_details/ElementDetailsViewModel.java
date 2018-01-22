package com.example.davit_zakaryan.mvvmapp.ui.element_details;

import com.example.davit_zakaryan.mvvmapp.data.DataSource;
import com.example.davit_zakaryan.mvvmapp.data.model.Element;
import com.example.davit_zakaryan.mvvmapp.ui.base.BaseViewModel;

import javax.inject.Inject;

import io.reactivex.disposables.CompositeDisposable;

/**
 * Created by Davit_Zakaryan on 12/8/2017.
 */
public class ElementDetailsViewModel extends BaseViewModel {

	private Element element;

	@Inject
	public ElementDetailsViewModel(DataSource dataSource, CompositeDisposable disposables) {
		super(dataSource, disposables);
	}

	public void setElement(Element element) {
		this.element = element;
	}

	public Element getElement() {
		return element;
	}

	@Override
	public void onStart() {

	}
}
