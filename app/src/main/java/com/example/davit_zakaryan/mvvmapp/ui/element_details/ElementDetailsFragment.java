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
import com.example.davit_zakaryan.mvvmapp.R;
import com.example.davit_zakaryan.mvvmapp.data.model.Element;
import com.example.davit_zakaryan.mvvmapp.databinding.FragmentElementDetailsBinding;
import com.example.davit_zakaryan.mvvmapp.ui.base.BaseFragment;
import com.example.davit_zakaryan.mvvmapp.util.Constants;

import javax.inject.Inject;

import io.reactivex.subjects.PublishSubject;

/**
 * Created by Davit_Zakaryan on 12/8/2017.
 */

public class ElementDetailsFragment extends BaseFragment {

	private int chosenType;

	@Inject
	ElementDetailsViewModel elementDetailsViewModel;

	@Override
	public void onCreate(@Nullable Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setHasOptionsMenu(true);

		initActionBar();

		// ViewModel creation
		App.get(getActivity()).getAppComponent().inject(this);
	}

	@Nullable
	@Override
	public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
		FragmentElementDetailsBinding binding = DataBindingUtil.inflate(inflater, R.layout.fragment_element_details, container, false);
		binding.setViewModel(elementDetailsViewModel);
		return binding.getRoot();
	}

	private void initActionBar() {
		chosenType = 0;
		if (getArguments() != null) {
			chosenType = getArguments().getInt(Constants.EXTRA_CHOSEN_TYPE);
		}
		getActivity().setTitle(chosenType);

		// Show the Up button in the action bar.
		ActionBar actionBar = ((AppCompatActivity) getActivity()).getSupportActionBar();
		if (actionBar != null) {
			actionBar.setDisplayHomeAsUpEnabled(true);
			actionBar.setDisplayShowHomeEnabled(true);
		}
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

	public void setElementStream(PublishSubject<Element> elementStream) {
		elementDetailsViewModel.setElementPublishSubject(elementStream);
	}
}
