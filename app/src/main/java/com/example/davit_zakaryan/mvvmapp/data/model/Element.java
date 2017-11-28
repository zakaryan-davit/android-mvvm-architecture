package com.example.davit_zakaryan.mvvmapp.data.model;

import android.databinding.BaseObservable;
import android.databinding.ObservableInt;

/**
 * Created by Davit_Zakaryan on 11/22/2017.
 */

public class Element extends BaseObservable {
	public String id;
	//public int level;
	public ObservableInt level = new ObservableInt();
	public String name;
	public String shortDesc;
	public String description;
	public String url;

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
