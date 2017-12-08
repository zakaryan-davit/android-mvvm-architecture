package com.example.davit_zakaryan.mvvmapp.ui.elements;

import android.os.Bundle;
import android.support.annotation.Nullable;

import com.example.davit_zakaryan.mvvmapp.R;
import com.example.davit_zakaryan.mvvmapp.ui.base.BaseActivity;

public class ElementsActivity extends BaseActivity {

	@Override
	protected void onCreate(@Nullable Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_elements);

		// Check whether the Activity is using the layout version with the fragment_container
		// FrameLayout and if so we must add the first fragment
		if (findViewById(R.id.fragment_container) != null) {

			// However if we are being restored from a previous state, then we don't
			// need to do anything and should return or we could end up with overlapping Fragments
			if (savedInstanceState != null) {
				return;
			}

			// Create an Instance of Fragment
			ElementsFragment elementsFragment = new ElementsFragment();
			elementsFragment.setArguments(getIntent().getExtras());
			getSupportFragmentManager()
					.beginTransaction()
					.add(R.id.fragment_container, elementsFragment)
					.commit();
		}
	}
}
