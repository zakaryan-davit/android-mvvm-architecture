package com.example.davit_zakaryan.mvvmapp.ui.element_details;

import android.databinding.ObservableField;
import android.view.View;

import com.example.davit_zakaryan.mvvmapp.data.DataSource;
import com.example.davit_zakaryan.mvvmapp.data.model.Element;
import com.example.davit_zakaryan.mvvmapp.ui.base.BaseViewModel;

import javax.inject.Inject;

import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.functions.Consumer;
import io.reactivex.subjects.PublishSubject;

/**
 * Created by Davit_Zakaryan on 12/8/2017.
 */
public class ElementDetailsViewModel extends BaseViewModel {

	private ObservableField<Element> elementObservable;

	@Inject
	public ElementDetailsViewModel(DataSource dataSource, CompositeDisposable disposables) {
		super(dataSource, disposables);
		this.elementObservable = new ObservableField<>();
	}

	public Element getElement() {
		return elementObservable.get();
	}

	public void setElement(Element element) {
		elementObservable.set(element);
	}

	@Override
	public void onStart() {

	}

	public void setElementPublishSubject(PublishSubject<Element> elementPublishSubject) {
		elementPublishSubject.subscribe(new Consumer<Element>() {
			@Override
			public void accept(Element element) throws Exception {
				setElement(element);
			}
		});
	}

	// TODO should be removed to some Handlers class
	public void onClickButtonFab(View view) {
		System.out.println("Fab click");
		elementObservable.get().incrementLevel();
	}
}
