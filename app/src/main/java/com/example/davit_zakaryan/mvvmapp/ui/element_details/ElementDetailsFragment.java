package com.example.davit_zakaryan.mvvmapp.ui.element_details;

import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.ActionBar;
import android.support.v7.widget.Toolbar;
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


	@Nullable
	@Override
	public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

		FragmentElementDetailsBinding binding = DataBindingUtil.inflate(inflater, R.layout.fragment_element_details, container, false);
		((BaseActivity) getActivity()).setSupportActionBar((Toolbar) binding.toolbar);


		int chosenType = getArguments().getInt(Constants.EXTRA_CHOSEN_TYPE, 0);
		getActivity().setTitle(chosenType);

		Element element = FakeData.getElementInstance();


		binding.setElement(element);

		// Show the Up button in the action bar.
		ActionBar actionBar = ((BaseActivity) getActivity()).getSupportActionBar();
		if (actionBar != null) {
			actionBar.setDisplayHomeAsUpEnabled(true);
			actionBar.setDisplayShowHomeEnabled(true);
		}

		return binding.getRoot();
	}
}
