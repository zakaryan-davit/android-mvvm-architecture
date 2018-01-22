package com.example.davit_zakaryan.mvvmapp.ui.base;


import com.example.davit_zakaryan.mvvmapp.data.DataSource;

import io.reactivex.disposables.CompositeDisposable;

public abstract class BaseViewModel {

	protected final DataSource dataSource;

	protected CompositeDisposable disposables;

	protected BaseViewModel(DataSource dataSource, CompositeDisposable compositeDisposable) {
		this.dataSource = dataSource;
		this.disposables = compositeDisposable;
	}

	protected abstract void onStart();

	protected void onStop() {
		disposables.dispose();
	}
}
