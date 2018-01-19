package com.example.davit_zakaryan.mvvmapp.ui.base;


import com.example.davit_zakaryan.mvvmapp.data.DataSource;

public abstract class BaseViewModel {

	protected final DataSource dataSource;

	protected BaseViewModel(DataSource dataSource) {
		this.dataSource = dataSource;
	}

	protected abstract void onStart();

	protected abstract void onStop();
}
