package com.example.davit_zakaryan.mvvmapp.ui.element_form;

import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.ActionBar;
import android.support.v7.widget.Toolbar;

import com.example.davit_zakaryan.mvvmapp.R;
import com.example.davit_zakaryan.mvvmapp.data.model.Element;
import com.example.davit_zakaryan.mvvmapp.databinding.ActivityElementFormBinding;
import com.example.davit_zakaryan.mvvmapp.ui.base.BaseActivity;

/**
 * Created by Davit_Zakaryan on 11/28/2017.
 */

public class ElementFormActivity extends BaseActivity {

	private Element element;

	@Override
	protected void onCreate(@Nullable Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

		element = new Element();
		element.url = "http://www.planwallpaper.com/static/images/desktop-year-of-the-tiger-images-wallpaper.jpg";
		element.description = "";

		ActivityElementFormBinding binding = DataBindingUtil.setContentView(this, R.layout.activity_element_form);
		binding.setElement(element);

		Toolbar toolbar = findViewById(R.id.toolbar);
		setSupportActionBar(toolbar);

		// Show the Up button in the action bar.
		ActionBar actionBar = getSupportActionBar();
		if (actionBar != null) {
			actionBar.setDisplayHomeAsUpEnabled(true);
			actionBar.setDisplayShowHomeEnabled(true);
		}
	}
}
