package com.example.davit_zakaryan.mvvmapp.data.model;

import android.databinding.ObservableField;
import android.databinding.ObservableInt;

public class Element {

	public ObservableField<String> id = new ObservableField<>();
	public ObservableInt level = new ObservableInt();
	public ObservableField<String> name = new ObservableField<>();
	public ObservableField<String> shortDesc = new ObservableField<>();
	public ObservableField<String> description = new ObservableField<>();
	public ObservableField<String> url = new ObservableField<>();

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
