package com.example.davit_zakaryan.mvvmapp.ui.base;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.widget.Toolbar;

import com.example.davit_zakaryan.mvvmapp.R;
import com.example.davit_zakaryan.mvvmapp.ui.element_details.ElementDetailsFragment;
import com.example.davit_zakaryan.mvvmapp.ui.elements.ElementsFragment;

public class HomeActivity extends BaseActivity implements OnElementSelectionChangeListener {

	// TODO inject this
	private HomeViewModel homeViewModel;

	@Override
	protected void onCreate(@Nullable Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_home);

		homeViewModel = new HomeViewModel();

		Toolbar toolbar = findViewById(R.id.toolbar);
		setSupportActionBar(toolbar);

		// Check whether the Activity is using the layout version with the
		// fragment_container FrameLayout and if so we must add the first fragment
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
		} else {

			ElementsFragment elementsFragment = (ElementsFragment) getSupportFragmentManager()
					.findFragmentById(R.id.elements_fragment);
			ElementDetailsFragment detailsFragment = (ElementDetailsFragment) getSupportFragmentManager()
					.findFragmentById(R.id.element_details_fragment);


			if (detailsFragment != null) {
				detailsFragment.setElementStream(elementsFragment.getElementStream());
			}
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
		super.setTitle(titleId);
	}

	@Override
	public void onSelectionChanged(int index) {
		ElementDetailsFragment detailsFragment = (ElementDetailsFragment) getSupportFragmentManager()
				.findFragmentById(R.id.element_details_fragment);

		System.out.println("index = " + index);

		if (detailsFragment != null) {
			// If details is available, we are in two pane layout
			// so we call the method in ElementDetailsFragment to update its content
			///detailsFragment.setElement(index);
		} else {
			ElementDetailsFragment newDetailsFragment = new ElementDetailsFragment();
			Bundle args = new Bundle();

			///args.putInt(ElementDetailsFragment.KEY_POSITION, index);
			newDetailsFragment.setArguments(args);
			FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();

			// Replace whatever is in the fragment_container view with this fragment,
			// and add the transaction to the backStack so the User can navigate back
			fragmentTransaction.replace(R.id.fragment_container, newDetailsFragment);
			fragmentTransaction.addToBackStack(null);
			fragmentTransaction.commit();
		}
	}
}
