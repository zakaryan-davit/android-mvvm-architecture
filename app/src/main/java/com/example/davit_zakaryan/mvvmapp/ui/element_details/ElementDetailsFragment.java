package com.example.davit_zakaryan.mvvmapp.ui.element_details;

import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.ActionBar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.davit_zakaryan.mvvmapp.FakeData;
import com.example.davit_zakaryan.mvvmapp.R;
import com.example.davit_zakaryan.mvvmapp.data.model.Element;
import com.example.davit_zakaryan.mvvmapp.databinding.FragmentElementDetailsBinding;
import com.example.davit_zakaryan.mvvmapp.ui.base.BaseActivity;
import com.example.davit_zakaryan.mvvmapp.ui.base.BaseFragment;
import com.example.davit_zakaryan.mvvmapp.util.Constants;

/**
 * Created by Davit_Zakaryan on 12/8/2017.
 */

public class ElementDetailsFragment extends BaseFragment {


	public static String KEY_POSITION = "position";
	private Element element;
	private ElementDetailsViewModel viewModel;

	@Nullable
	@Override
	public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

		viewModel = new ElementDetailsViewModel();

		FragmentElementDetailsBinding binding = DataBindingUtil.inflate(inflater, R.layout.fragment_element_details, container, false);

		int chosenType = 0;
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
		ActionBar actionBar = ((BaseActivity) getActivity()).getSupportActionBar();
		if (actionBar != null) {
			actionBar.setDisplayHomeAsUpEnabled(true);
			actionBar.setDisplayShowHomeEnabled(true);
		}

		return binding.getRoot();
	}

	public void setElement(int index) {
		element = FakeData.getElementList().get(index);
		viewModel.setElement(element);
	}
}
