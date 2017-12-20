package com.example.davit_zakaryan.mvvmapp.data.model;

import android.databinding.ObservableField;
import android.databinding.ObservableInt;

/**
 * Created by Davit_Zakaryan on 11/22/2017.
 */

public class Element {

	public ObservableField<String> id;
	public ObservableInt level = new ObservableInt();
	public ObservableField<String> name;
	public ObservableField<String> shortDesc;
	public ObservableField<String> description;
	public ObservableField<String> url;

	public void incrementLevel() {
		if (level.get() < 10) {
			level.set(level.get() + 1);
		}
	}

	public void decrementLevel() {
		if (level.get() > 1) {
			level.set(level.get() - 1);
		}
	}
}
