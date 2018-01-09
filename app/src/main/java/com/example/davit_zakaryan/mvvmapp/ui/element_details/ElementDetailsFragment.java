package com.example.davit_zakaryan.mvvmapp.ui.element_details;

import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;

import com.example.davit_zakaryan.mvvmapp.App;
import com.example.davit_zakaryan.mvvmapp.FakeData;
import com.example.davit_zakaryan.mvvmapp.R;
import com.example.davit_zakaryan.mvvmapp.data.model.Element;
import com.example.davit_zakaryan.mvvmapp.databinding.FragmentElementDetailsBinding;
import com.example.davit_zakaryan.mvvmapp.ui.base.BaseFragment;
import com.example.davit_zakaryan.mvvmapp.util.Constants;
import com.example.davit_zakaryan.mvvmapp.util.RxBus;

import javax.inject.Inject;

/**
 * Created by Davit_Zakaryan on 12/8/2017.
 */

public class ElementDetailsFragment extends BaseFragment {


	public static String KEY_POSITION = "position";
	private Element element;
	private int chosenType;

	@Inject
	ElementDetailsViewModel viewModel;

	@Inject
	RxBus rxBus;

	@Override
	public void onCreate(@Nullable Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setHasOptionsMenu(true);

		// ViewModel creation
		App.get(getActivity()).getAppComponent().inject(this);
	}

	@Nullable
	@Override
	public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

		FragmentElementDetailsBinding binding = DataBindingUtil.inflate(inflater, R.layout.fragment_element_details, container, false);
		chosenType = 0;
		int selectedIndex = 0;
		if (getArguments() != null) {
			chosenType = getArguments().getInt(Constants.EXTRA_CHOSEN_TYPE);
			selectedIndex = getArguments().getInt(KEY_POSITION);
		}
		getActivity().setTitle(chosenType);


		element = FakeData.getElementList().get(selectedIndex);
		viewModel.setElement(element);


		binding.setViewModel(viewModel);

		// Show the Up button in the action bar.
		ActionBar actionBar = ((AppCompatActivity) getActivity()).getSupportActionBar();
		if (actionBar != null) {
			actionBar.setDisplayHomeAsUpEnabled(true);
			actionBar.setDisplayShowHomeEnabled(true);
		}

		return binding.getRoot();
	}


	@Override
	public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
		if (chosenType == 2) {
			inflater.inflate(R.menu.element_details, menu);
		}
		//super.onCreateOptionsMenu(menu, inflater);
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		switch (item.getItemId()) {
			case R.id.action_alarm:
				break;
		}
		return super.onOptionsItemSelected(item);
	}

	public void setElement(int index) {
		element = FakeData.getElementList().get(index);
		viewModel.setElement(element);
	}
}
