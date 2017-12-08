package com.example.davit_zakaryan.mvvmapp.ui.base;

import android.os.Bundle;
import android.support.annotation.Nullable;

import com.example.davit_zakaryan.mvvmapp.R;
import com.example.davit_zakaryan.mvvmapp.ui.elements.ElementsFragment;

public class HomeActivity extends BaseActivity {

	@Override
	protected void onCreate(@Nullable Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_home);

		// Check whether the Activity is using the layout version with the fragment_container FrameLayout and if so we must add the first fragment
		if (findViewById(R.id.fragment_container) != null) {

			if (savedInstanceState != null) {
				return;
			}

			// Create an Instance of Fragment
			ElementsFragment elementsFragment = new ElementsFragment();
			elementsFragment.setArguments(getIntent().getExtras());
			getSupportFragmentManager().beginTransaction()
					.add(R.id.fragment_container, elementsFragment)
					.commit();
		}
	}

	@Override
	public void setTitle(int chosenType) {
		int titleId;
		switch (chosenType) {
			case 0:
				titleId = R.string.snakes;
				break;
			case 1:
				titleId = R.string.cards;
				break;
			case 2:
				titleId = R.string.tasks;
				break;
			default:
				titleId = R.string.snakes;
				break;
		}
		setTitle(titleId);
	}
}
