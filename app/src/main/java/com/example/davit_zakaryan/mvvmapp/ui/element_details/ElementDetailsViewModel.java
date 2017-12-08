package com.example.davit_zakaryan.mvvmapp.ui.element_details;

import android.databinding.BaseObservable;

import com.example.davit_zakaryan.mvvmapp.data.model.Element;

/**
 * Created by Davit_Zakaryan on 12/8/2017.
 */
public class ElementDetailsViewModel extends BaseObservable {

	private Element element;

	public void setElement(Element element) {
		this.element = element;
		notifyChange();
	}

	public Element getElement() {
		return element;
	}
}
