package com.example.davit_zakaryan.mvvmapp.ui.element_form;

import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.ActionBar;
import android.support.v7.widget.Toolbar;
import android.view.WindowManager;

import com.example.davit_zakaryan.mvvmapp.R;
import com.example.davit_zakaryan.mvvmapp.data.service.IRepository;
import com.example.davit_zakaryan.mvvmapp.databinding.ActivityElementFormBinding;
import com.example.davit_zakaryan.mvvmapp.ui.base.BaseActivity;
import com.example.davit_zakaryan.mvvmapp.ui.elements.RepositoryImpl;

/**
 * Created by Davit_Zakaryan on 11/28/2017.
 */

public class ElementFormActivity extends BaseActivity {

	private ElementFormViewModel elementFormViewModel;

	@Override
	protected void onCreate(@Nullable Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

		IRepository repository = new RepositoryImpl();
		elementFormViewModel = new ElementFormViewModel(repository, ElementFormActivity.this, false);

		getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_HIDDEN);

		ActivityElementFormBinding binding = DataBindingUtil.setContentView(this, R.layout.activity_element_form);
		binding.setViewModel(elementFormViewModel);

		Toolbar toolbar = findViewById(R.id.toolbar);
		setSupportActionBar(toolbar);

		// Show the Up button in the action bar.
		ActionBar actionBar = getSupportActionBar();
		if (actionBar != null) {
			actionBar.setDisplayHomeAsUpEnabled(true);
			actionBar.setDisplayShowHomeEnabled(true);
		}
	}

	@Override
	protected void onStart() {
		super.onStart();
		elementFormViewModel.onStart();
	}
}
